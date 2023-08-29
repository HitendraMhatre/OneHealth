//package com.oneHealth.blog.controller;
//
//import java.sql.Timestamp;
//import java.time.Instant;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.oneHealth.blog.Exception.BlogRatingNotFoundException;
//import com.oneHealth.blog.entities.BlogRating;
//import com.oneHealth.blog.services.BlogRatingService;
///**
// * The BlogRatingController class handles the API endpoints related to BlogRating operations.
// */
//@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://onestop-fe-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
//public class BlogRatingController {
//
//    @Autowired
//    BlogRatingService service;
//
//    /**
//     * Retrieves all the BlogRatings.
//     *
//     * @return ResponseEntity with the list of BlogRating objects if they exist,
//     *         or a no content response if no BlogRatings are found.
//     */
//    @GetMapping(value = "api/BlogRating")
//    public ResponseEntity<List<BlogRating>> showBlogRatings() {
//        List<BlogRating> BlogRatings = service.getBlogRatings();
//        if (BlogRatings.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.ok(BlogRatings);
//        }
//    }
//
//    /**
//     * Retrieves a specific BlogRating by its ID.
//     *
//     * @param deptId The ID of the BlogRating to retrieve.
//     * @return ResponseEntity with the BlogRating if found,
//     *         or throws BlogRatingNotFoundException if the BlogRating is not found.
//     */
//    @GetMapping(value = "api/BlogRatingById/{deptId}")
//    public ResponseEntity<BlogRating> getBlogRating(@PathVariable int deptId) {
//        try {
//            Optional<BlogRating> BlogRating = service.getBlogRating(deptId);
//            return BlogRating.map(ResponseEntity::ok).orElseThrow(() ->
//                    new BlogRatingNotFoundException("BlogRating not found with ID: " + deptId));
//        } catch (BlogRatingNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(null); 
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
//
//    /**
//     * Retrieves a specific BlogRating by its ID.
//     *
//     * @param deptId The ID of the BlogRating to retrieve.
//     * @return ResponseEntity with the BlogRating if found,
//     *         or throws BlogRatingNotFoundException if the BlogRating is not found.
//     */
//    @GetMapping(value = "api/BlogRatingByPostId/{postId}/{userId}")
//    public ResponseEntity<BlogRating> getBlogRatingByPID(@PathVariable int postId, @PathVariable int userId) {
//        try {
//            Optional<BlogRating> blogRating = service.getBlogRatingByPID(postId, userId);
//            return blogRating.map(ResponseEntity::ok).orElseThrow(() ->
//                    new BlogRatingNotFoundException("BlogRating not found with Post ID: " + postId + " and User ID: " + userId));
//        } catch (BlogRatingNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    
//    @GetMapping(value = "api/AvgBlogRatingByPostId/{postId}")
//    public ResponseEntity<Double> getAvgBlogRatingByPID(@PathVariable int postId) {
//        try {
//            Double avgBlogRating = service.getPostAverageRating(postId);
//            
//            if (avgBlogRating != null) {
//                return ResponseEntity.ok(avgBlogRating);
//            } else {
//                throw new BlogRatingNotFoundException("BlogRating not found with ID: " + postId);
//            }
//        } catch (BlogRatingNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    
//    /**
//     * Removes a BlogRating by its ID.
//     *
//     * @param deptId The ID of the BlogRating to remove.
//     * @return ResponseEntity with a success message if the BlogRating is deleted successfully,
//     *         or an error message if the BlogRating deletion fails.
//     */
//    @DeleteMapping(value = "api/BlogRating/{ratingId}")
//    public ResponseEntity<String> removeBlogRating(@PathVariable int ratingId) {
//        try {
//            service.delete(ratingId);
//            return ResponseEntity.ok("BlogRating deleted successfully.");
//        } catch (BlogRatingNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("BlogRating not found with ID: " + ratingId);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to delete BlogRating: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Adds a new BlogRating.
//     *
//     * @param BlogRating The BlogRating object to add.
//     * @return ResponseEntity with a success message if the BlogRating is added successfully,
//     *         or an error message if the BlogRating addition fails.
//     */
//    @PostMapping(value = "api/BlogRating")
//    public ResponseEntity<String> addBlogRating(@RequestBody BlogRating BlogRating) {
//        try {
//        	// Set the registration timestamp to the current time
//            Timestamp currentTimestamp = Timestamp.from(Instant.now());
//            BlogRating.setCreationTimeStamp(currentTimestamp);
//            BlogRating.setEditedTimeStamp(currentTimestamp);
//            service.addBlogRating(BlogRating);
//            return ResponseEntity.ok("BlogRating added successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to add BlogRating: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Updates an existing BlogRating by its ID.
//     *
//     * @param BlogRating The updated BlogRating object.
//     * @param deptId     The ID of the BlogRating to update.
//     * @return ResponseEntity with a success message if the BlogRating is updated successfully,
//     *         or an error message if the BlogRating update fails.
//     */
//    @PutMapping(value = "api/BlogRating/{ratingId}")
//    public ResponseEntity<String> updateBlogRating(@RequestBody BlogRating BlogRating, @PathVariable int ratingId) {
//        try {
//            service.update(BlogRating, ratingId);
//            return ResponseEntity.ok("BlogRating details modified successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to modify BlogRating details: " + e.getMessage());
//        }
//    }
//}
//
