package ch.simas.jtoggl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserWithRelatedData extends User{

    private List<TimeEntry> time_entries = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private List<Workspace> workspaces = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    public UserWithRelatedData() {
        super();
    }

    public UserWithRelatedData(String jsonString) {
        super(jsonString);
        JSONObject object = (JSONObject) JSONValue.parse(jsonString);

        JSONArray tagsJArr = (JSONArray) object.get("tags");
        if (tagsJArr != null) {
            for (int i=0;i<tagsJArr.size();i++){
                JSONObject obj = (JSONObject) tagsJArr.get(i);
                tags.add(new Tag(obj.toJSONString()));
            }
        }

        JSONArray timeEntriesJArr = (JSONArray) object.get("time_entries");
        if (timeEntriesJArr != null) {
            for (int i=0;i<timeEntriesJArr.size();i++){
                JSONObject obj = (JSONObject) timeEntriesJArr.get(i);
                time_entries.add(new TimeEntry(obj.toJSONString()));
            }
        }

        JSONArray projectsJArr = (JSONArray) object.get("projects");
        if (projectsJArr != null) {
            for (int i=0;i<projectsJArr.size();i++){
                JSONObject obj = (JSONObject) projectsJArr.get(i);
                projects.add(new Project(obj.toJSONString()));
            }
        }

        JSONArray clientsJArr = (JSONArray) object.get("clients");
        if (clientsJArr != null) {
            for (int i=0;i<clientsJArr.size();i++){
                JSONObject obj = (JSONObject) clientsJArr.get(i);
                clients.add(new Client(obj.toJSONString()));
            }
        }

        JSONArray workspacesJArr = (JSONArray) object.get("workspaces");
        if (workspacesJArr != null) {
            for (int i=0;i<workspacesJArr.size();i++){
                JSONObject obj = (JSONObject) workspacesJArr.get(i);
                workspaces.add(new Workspace(obj.toJSONString()));
            }
        }
    }

    public List<TimeEntry> getTime_entries() {
        return time_entries;
    }

    public void setTime_entries(List<TimeEntry> time_entries) {
        this.time_entries = time_entries;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Workspace> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List<Workspace> workspaces) {
        this.workspaces = workspaces;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        object.put("projects", projects);
        object.put("time_entries", time_entries);
        object.put("clients", clients);
        object.put("tags", tags);
        object.put("workspaces", workspaces);
        return object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserWithRelatedData)) return false;
        if (!super.equals(o)) return false;
        UserWithRelatedData that = (UserWithRelatedData) o;
        return Objects.equals(getProjects(), that.getProjects()) &&
                Objects.equals(getTags(), that.getTags()) &&
                Objects.equals(getWorkspaces(), that.getWorkspaces()) &&
                Objects.equals(getClients(), that.getClients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getProjects(), getTags(), getWorkspaces(), getClients());
    }

    @Override
    public String toString() {
        return "UserWithRelatedData{" +
                "time_entries=" + time_entries +
                ", projects=" + projects +
                ", tags=" + tags +
                ", workspaces=" + workspaces +
                ", clients=" + clients +
                "}";
    }

    public String toJSONString() {
        return this.toJSONObject().toJSONString();
    }
}
