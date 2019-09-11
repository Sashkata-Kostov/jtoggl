package ch.simas.jtoggl.api.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeEntry {

    private Client client;
    private Project project;
    private Activity activity;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private Long duration;

    public TimeEntry() {
    }

    public TimeEntry(Client client, Project project, Activity activity, LocalDateTime start_date, LocalDateTime end_date) {
        this.client = client;
        this.project = project;
        this.activity = activity;
        this.start_date = start_date;
        this.end_date = end_date;
        this.duration = calculateDuration();
    }

    private Long calculateDuration() {
        LocalDateTime temp = LocalDateTime.from(start_date);
        long seconds = temp.until(end_date, ChronoUnit.SECONDS);
        return seconds * 1000;
    }

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

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "TimeEntry{" +
                "client=" + client +
                ", project=" + project +
                ", activity=" + activity +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeEntry)) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return Objects.equals(getClient(), timeEntry.getClient()) &&
                getProject().equals(timeEntry.getProject()) &&
                Objects.equals(getActivity(), timeEntry.getActivity()) &&
                getStart_date().equals(timeEntry.getStart_date()) &&
                getEnd_date().equals(timeEntry.getEnd_date()) &&
                Objects.equals(getDuration(), timeEntry.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClient(), getProject(), getActivity(), getStart_date(), getEnd_date(), getDuration());
    }
}
