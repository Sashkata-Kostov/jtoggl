package ch.simas.jtoggl.api;

import ch.simas.jtoggl.JToggl;
import ch.simas.jtoggl.Tag;
import ch.simas.jtoggl.TimeEntry;
import ch.simas.jtoggl.UserWithRelatedData;
import ch.simas.jtoggl.api.model.Activity;
import ch.simas.jtoggl.api.model.Client;
import ch.simas.jtoggl.api.model.Project;
import ch.simas.jtoggl.api.model.ProjectEntry;
import ch.simas.jtoggl.util.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JTogglApi {

    public List<ProjectEntry> getProjectEntriesForUser(String userApiToken, LocalDateTime start_date, LocalDateTime end_date){
        JToggl toggl = new JToggl(userApiToken);
        UserWithRelatedData user = toggl.getCurrentUserWithRelatedData();
        List<TimeEntry> time_entries = toggl.getTimeEntries(DateUtil.asDate(start_date), DateUtil.asDate(end_date));
        List<ProjectEntry> projectEntries = getProjectEntriesForTimeEntries(time_entries, user);
        return projectEntries;
    }

    private List<ProjectEntry> getProjectEntriesForTimeEntries(List<TimeEntry> time_entries, UserWithRelatedData user) {
        List<ProjectEntry> toReturn = new ArrayList<>();
        for (TimeEntry entry: time_entries) {
            ProjectEntry projectEntry = new ProjectEntry();
            Activity activity = getActivityFromTimeEntry(entry, user);
            Project project = getProjectFromTimeEntry(entry, user);
            Client client = getClientFromTimeEntry(entry, user);
            String id = client.getId() + "_" + project.getId() + "_" + activity.getId();
            projectEntry.setClient(client);
            projectEntry.setProject(project);
            projectEntry.setActivity(activity);
            projectEntry.setId(id);
            if (!toReturn.contains(projectEntry)) {
                toReturn.add(projectEntry);
            }
        }
        return toReturn;
    }

    private Client getClientFromTimeEntry(TimeEntry entry, UserWithRelatedData user) {
        ch.simas.jtoggl.Client temp = null;
        ch.simas.jtoggl.Project proj = null;
        for (ch.simas.jtoggl.Project project : user.getProjects()) {
            if (project.getId().equals(entry.getPid())){
                proj = project;
            }
        }
        for (ch.simas.jtoggl.Client client: user.getClients()) {
            if (client.getId().equals(proj.getCid())) {
                temp = client;
            }
        }
        return new Client(temp.getId().toString(), temp.getName());

    }

    private Project getProjectFromTimeEntry(TimeEntry entry, UserWithRelatedData user) {
        Project temp = new Project();
        ch.simas.jtoggl.Project proj = null;
        for (ch.simas.jtoggl.Project project : user.getProjects()) {
            if (project.getId().equals(entry.getPid())){
                proj = project;
            }
        }
        temp.setName(proj.getName());
        temp.setId(proj.getId().toString());
        return temp;
    }

    private Activity getActivityFromTimeEntry(TimeEntry entry, UserWithRelatedData user) {
        if(!entry.getTag_names().isEmpty()) {
            String activityName = entry.getTag_names().get(0);
            for (Tag tag: user.getTags()) {
                if(tag.getName().equals(activityName)) {
                    return new Activity(tag.getId().toString(), activityName);
                }
            }
        }
        return null;
    }

}
