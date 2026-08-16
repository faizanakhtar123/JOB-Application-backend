package com.faizan.job.service;

import com.faizan.job.model.JobPost;
import com.faizan.job.repo.JobRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private  final JobRepo jobRepo;
    public JobService (JobRepo jobRepo){
        this.jobRepo = jobRepo;
    }
    // Load sample data when Application starts
    @PostConstruct
    public void load() {
        JobPost job1 = new JobPost(
                1,
                "Java Developer",
                "Backend Developer",
                2,
                List.of("Java", "Spring Boot", "SQL")
        );

        JobPost job2 = new JobPost(
                2,
                "Frontend Developer",
                "React Developer",
                1,
                List.of("HTML", "CSS", "JavaScript", "React")
        );
        jobRepo.save(job2);
        jobRepo.save(job1);
    }

    // Get job by ID
    public JobPost getJobById(int postId) {
        return jobRepo.findById(postId).orElse(null);
    }
    // Add job
    public JobPost addJob(JobPost jobPost) {
       return jobRepo.save(jobPost);

    }
    // Get All Job
    public List<JobPost> getAllJobs() {
       return jobRepo.findAll();
    }
    // Update Job
    public JobPost updateJob(int postId, JobPost jobPost) {
       JobPost existingById = jobRepo.findById(postId).orElse(null);
       if(existingById!=null){
           existingById.setPostProfile(jobPost.getPostProfile());
           existingById.setPostDesc(jobPost.getPostDesc());
           existingById.setReqExperience(jobPost.getReqExperience());
           existingById.setPostTechStack(jobPost.getPostTechStack());
           return jobRepo.save(existingById);
       }
       return null;
    }
    // Delete Job
    public String deleteJob(int postId) {
        if(jobRepo.existsById(postId)){
            jobRepo.deleteById(postId);
            return "Job is delete successfully";
        }
        return "Job not found !!";
    }


    public List<JobPost> searchByKeyword(String keyword) {
        return jobRepo.findByPostProfileContainingIgnoreCaseOrPostDescContainingIgnoreCase(
                keyword,
                keyword );
    }
}