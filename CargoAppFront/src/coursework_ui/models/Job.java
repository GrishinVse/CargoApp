package coursework_ui.models;

import com.google.gson.Gson;
import javafx.beans.property.*;

import java.util.HashMap;
import java.util.Map;

public class Job implements JSONSerialize {
    private final LongProperty id;
    private final StringProperty title;
    private final StringProperty minSalary;

    public Job() {
        this(null, null, null);
    }

    public Job(Long id, String title, String minSalary) {
        this.id = new SimpleLongProperty(id);
        this.title = new SimpleStringProperty(title);
        this.minSalary = new SimpleStringProperty(minSalary);
    }

    public Job(String title, String minSalary) {
        this.id = null;
        this.title = new SimpleStringProperty(title);
        this.minSalary = new SimpleStringProperty(minSalary);
    }

    // Getters

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getMinSalary() {
        return minSalary.get();
    }

    public StringProperty minSalaryProperty() {
        return minSalary;
    }

    // Setters

    public void setId(long id) {
        this.id.set(id);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setMinSalary(String minSalary) {
        this.minSalary.set(minSalary);
    }

    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        if (id == null){
            System.out.printf("ID of new Job ", this.getClass(), " is NULL!");
            map.put("id", "");
        } else {
            System.out.printf("ID of new Job ", this.getClass(), " is ", id.get());
            map.put("id", String.valueOf(id.get()));
        }
        map.put("title", title.get());
        map.put("min_salary", minSalary.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
