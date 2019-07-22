package com.timeline.api.application.service;

import com.timeline.api.infrastructure.repository.PostRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PostServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImplTest.class);

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void 일_유닉스시간_변환_테스트() {
        long time = DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH).getTime() / 1000;
        log.info(String.valueOf(time));
    }

    @Test
    public void 포스팅_테스트() {
        postService.savePost("tuguri8", "하이");
        assertThat(postRepository.findByUserIdAndTimestampDay("tuguri8", getCurrentDateTimestamp()).isPresent()).isTrue();
    }

    private Long getCurrentDateTimestamp() {
        return DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH).getTime() / 1000;
    }

}