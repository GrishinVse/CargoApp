package coursework_ui.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.beans.property.*;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Employee implements JSONSerialize{
    private final LongProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final ObjectProperty<Date> hireDate; // DATE UTILS
    private final FloatProperty rating;
    private final ObjectProperty<Job> jobId;

    public Employee(){
        this(null, null, null, null, null, null);
    }

    public Employee(Long id, String firstName, String lastName, Date hireDate, Float rating, Job jobId) {
        this.id = new SimpleLongProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.hireDate = new SimpleObjectProperty<Date>(hireDate);
        this.rating = new SimpleFloatProperty(rating);
        this.jobId = new SimpleObjectProperty<Job>(jobId);
    }

    // Getters

    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public Date getHireDate() {
        return hireDate.get();
    }

    public ObjectProperty<Date> hireDateProperty() {
        return hireDate;
    }

    public Float getRating() {
        return rating.get();
    }

    public FloatProperty ratingProperty() {
        return rating;
    }

    public Job getJobId() {
        return jobId.get();
    }

    public ObjectProperty<Job> jobIdProperty() {
        return jobId;
    }

    // Setters

    public void setId(Long id) {
        this.id.set(id);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setHireDate(Date hireDate) {
        this.hireDate.set(hireDate);
    }

    public void setRating(Float rating) {
        this.rating.set(rating);
    }

    public void setJobId(Job jobId) {
        this.jobId.set(jobId);
    }


    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        if (id == null){
            System.out.printf("ID of new Employee ", this.getClass(), " is NULL!");
            map.put("id", "");
        } else {
            System.out.printf("ID of new Employee ", this.getClass(), " is ", id.get());
            map.put("id", String.valueOf(id.get()));
        }

        map.put("first_name", firstName.get());
        map.put("last_name", lastName.get());
        map.put("hire_date", String.valueOf(hireDate.get()));
        map.put("rating", String.valueOf(rating.get()));
        map.put("job_id", new Gson().fromJson(jobId.get().toJson(), (Type) JsonObject.class));

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
