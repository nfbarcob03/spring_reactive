package clase2.StreamsExcercies.cedesistemas.model;

public class Project {
    private final String name;
    private final String team;
    private final String projectManager;

    public Project(String name, String team, String projectManager) {
        this.name = name;
        this.team = team;
        this.projectManager = projectManager;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getProjectManager() {
        return projectManager;
    }
}
