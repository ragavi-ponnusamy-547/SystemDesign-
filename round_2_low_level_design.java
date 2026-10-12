import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class InstagramController {

    Map<Integer, String> posts = new HashMap<>();
    List<String> follows = new ArrayList<>();
    List<String> likes = new ArrayList<>();
    List<String> comments = new ArrayList<>();

    // 1. Upload Media
    @PostMapping("/media")
    public String uploadMedia(@RequestBody Map<String, String> data) {
        int id = posts.size() + 1;
        posts.put(id, data.get("caption"));
        return "Media uploaded successfully. Post ID: " + id;
    }

    // 2. Get Post Details
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable int id) {
        return posts.getOrDefault(id, "Post not found");
    }

    // 3. Follow User
    @PostMapping("/users/{id}/follow")
    public String followUser(@PathVariable int id) {
        follows.add("Following user " + id);
        return "User followed successfully";
    }

    // 4. Like Post
    @PostMapping("/posts/{id}/likes")
    public String likePost(@PathVariable int id) {
        likes.add("Liked post " + id);
        return "Post liked successfully";
    }

    // 5. Add Comment
    @PostMapping("/posts/{id}/comments")
    public String addComment(
            @PathVariable int id,
            @RequestBody Map<String, String> data) {
        comments.add("Post " + id + ": " + data.get("comment"));
        return "Comment added successfully";
    }
}
