package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.Job;
import com.coursework.cargo_app.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService){ this.jobService = jobService; }

    @PostMapping(value = "/cargo_app/jobs")
    public ResponseEntity<?> create(@RequestBody Job job){
        jobService.create(job);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cargo_app/jobs")
    public ResponseEntity<List<Job>> findAll(){
        final List<Job> jobList = jobService.findAll();

        return jobList != null && !jobList.isEmpty()
                ? new ResponseEntity<>(jobList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cargo_app/jobs/{id}")
    public ResponseEntity<Optional<Job>> find(@PathVariable(name="id") Long id){
        final Optional<Job> job = jobService.find(id);

        return job != null
                ? new ResponseEntity<>(job, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cargo_app/jobs/{id}")
    public ResponseEntity<?> updateJob(@PathVariable(name = "id") Long id, @RequestBody Job updateJob) {
        return jobService.find(id).map(job -> {
            job.setTitle(updateJob.getTitle());
            job.setMin_salary(updateJob.getMin_salary());

            jobService.update(job);
            return new ResponseEntity<>(job, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/cargo_app/jobs/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable(name = "id") Long id) {
        return jobService.find(id).map(job -> {
            jobService.delete(job);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
