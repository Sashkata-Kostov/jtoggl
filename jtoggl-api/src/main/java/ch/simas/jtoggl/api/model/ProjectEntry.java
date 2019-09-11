package ch.simas.jtoggl.api.model;

import java.util.Objects;

public class ProjectEntry {

    private Client client;
    private Project project;
    private Activity activity;
    private String id;

    public ProjectEntry(Client client, Project project, Activity activity, String id) {
        this.client = client;
        this.project = project;
        this.activity = activity;
        this.id = id;
    }

    public ProjectEntry(){};

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProjectEntry{" +
                "client=" + client +
                ", project=" + project +
                ", activity=" + activity +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectEntry)) return false;
        ProjectEntry that = (ProjectEntry) o;
        return Objects.equals(getClient(), that.getClient()) &&
                getProject().equals(that.getProject()) &&
                Objects.equals(getActivity(), that.getActivity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClient(), getProject(), getActivity());
    }
}
