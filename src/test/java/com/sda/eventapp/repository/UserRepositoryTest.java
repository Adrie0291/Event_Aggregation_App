package com.sda.eventapp.repository;

import com.sda.eventapp.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class UserRepositoryTest {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ImageRepository imageRepository;
    private User testUser1;
    private User testUser2;



    @AfterEach
    void deleteTestDataFromDatabase() {
        commentRepository.deleteAll();
        imageRepository.deleteAll();
        eventRepository.deleteAll();
        userRepository.deleteAll();
    }
    @BeforeEach
    void prepareTestData() {
        testUser1 = User.builder()
                .username("name1")
                .email("user-test@gmail.com")
                .password("useruser")
                .build();
        testUser2 = User.builder()
                .username("name2")
                .email("user2-test@gmail.com")
                .password("user2user2")
                .build();
        userRepository.save(testUser1);
        userRepository.save(testUser2);
    }

    @Test
    void shouldfindByEmail() {
        User user = userRepository.findByEmail("user-test@gmail.com").get();
        assertEquals(user.getEmail(), "user-test@gmail.com");
    }

    @Test
    void isExistsEmailInDatabase() {
        boolean result = userRepository.existsByEmail("user2-test@gmail.com");
        assertTrue(result);
    }

    @Test
    void isNotExistsEmailInDatabase() {
        boolean result = userRepository.existsByEmail("user94@o2.pl");
        assertFalse(result);
    }

    @Test
    void isExistsByUsername() {
        boolean result = userRepository.existsByUsername("name1");
        assertTrue(result);
    }
    @Test
    void isNotexistsByUsername() {
        boolean result = userRepository.existsByUsername("name93");
        assertFalse(result);
    }
}