package com.faizan.job;

import com.faizan.job.model.JobPost;
import com.faizan.job.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {

    private final JobService jobService;

    public JobRestController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobPosts")
    public List<JobPost> getAllJobs() {
        return jobService.getAllJos();
    }
    @GetMapping("/jobPosts/{postId}")
    public JobPost getJobById(@PathVariable  int postId){
        return jobService.getJobById(postId);
    }
    @PostMapping("/jobPosts")
    public JobPost addJob(@RequestBody JobPost jobPost){
        return jobService.addJob(jobPost);
    }
    @PutMapping("/jobPosts/{postId}")
    public JobPost updateJob(
            @PathVariable int postId ,
            @RequestBody JobPost jobPost){
        return jobService.updateJob(postId,jobPost);
    }
    @DeleteMapping("/jobPosts/{postId}")
    public String deleteJob(@PathVariable int postId){
        return jobService.deleteJob(postId);
    }
   @GetMapping("/load")
    public String loadData(){
        jobService.load();
        return "success";
    }



}