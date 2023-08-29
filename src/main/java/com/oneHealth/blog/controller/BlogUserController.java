package com.oneHealth.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneHealth.blog.Exception.BlogUserNotFoundException;
import com.oneHealth.blog.entities.BlogUser;
import com.oneHealth.blog.services.BlogUserService;
/**
 * The BlogUserController class handles the API endpoints related to BlogUser operations.
 */
@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://onestop-fe-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
@RequestMapping("/blog/")
public class BlogUserController {

    @Autowired
    BlogUserService service;

    /**
     * Retrieves all the BlogUsers.
     *
     * @return ResponseEntity with the list of BlogUser objects if they exist,
     *         or a no content response if no BlogUsers are found.
     */
    @GetMapping(value = "api/BlogUser")
    public ResponseEntity<List<BlogUser>> showBlogUsers() {
        List<BlogUser> BlogUsers = service.getBlogUsers();
        if (BlogUsers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(BlogUsers);
        }
    }

    /**
     * Retrieves a specific BlogUser by its ID.
     *
     * @param deptId The ID of the BlogUser to retrieve.
     * @return ResponseEntity with the BlogUser if found,
     *         or throws BlogUserNotFoundException if the BlogUser is not found.
     */
    @GetMapping(value = "api/BlogUserById/{deptId}")
    public ResponseEntity<BlogUser> getBlogUser(@PathVariable int deptId) {
        try {
            Optional<BlogUser> BlogUser = service.getBlogUser(deptId);
            return BlogUser.map(ResponseEntity::ok).orElseThrow(() ->
                    new BlogUserNotFoundException("BlogUser not found with ID: " + deptId));
        } catch (BlogUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Removes a BlogUser by its ID.
     *
     * @param deptId The ID of the BlogUser to remove.
     * @return ResponseEntity with a success message if the BlogUser is deleted successfully,
     *         or an error message if the BlogUser deletion fails.
     */
    @DeleteMapping(value = "api/BlogUser/{deptId}")
    public ResponseEntity<String> removeBlogUser(@PathVariable int deptId) {
        try {
            service.delete(deptId);
            return ResponseEntity.ok("BlogUser deleted successfully.");
        } catch (BlogUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("BlogUser not found with ID: " + deptId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete BlogUser: " + e.getMessage());
        }
    }

    /**
     * Adds a new BlogUser.
     *
     * @param BlogUser The BlogUser object to add.
     * @return ResponseEntity with a success message if the BlogUser is added successfully,
     *         or an error message if the BlogUser addition fails.
     */
    @PostMapping(value = "api/BlogUser")
    public ResponseEntity<String> addBlogUser(@RequestBody BlogUser BlogUser) {
        try {
            service.addBlogUser(BlogUser);
            return ResponseEntity.ok("BlogUser added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add BlogUser: " + e.getMessage());
        }
    }

    /**
     * Updates an existing BlogUser by its ID.
     *
     * @param BlogUser The updated BlogUser object.
     * @param deptId     The ID of the BlogUser to update.
     * @return ResponseEntity with a success message if the BlogUser is updated successfully,
     *         or an error message if the BlogUser update fails.
     */
    @PutMapping(value = "api/BlogUser/{deptId}")
    public ResponseEntity<String> updateBlogUser(@RequestBody BlogUser BlogUser, @PathVariable int deptId) {
        try {
            service.update(BlogUser, deptId);
            return ResponseEntity.ok("BlogUser details modified successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to modify BlogUser details: " + e.getMessage());
        }
    }
}

