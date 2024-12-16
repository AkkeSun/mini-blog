package com.sweettracker.resourceserver.config;

import com.sweettracker.resourceserver.dao.BlogPostCommentEntity;
import com.sweettracker.resourceserver.dao.BlogPostEntity;
import com.sweettracker.resourceserver.dao.BlogPostRepository;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitConfig {

    private final BlogPostRepository blogPostRepository;

    @PostConstruct
    public void init() {
        BlogPostEntity post1 = BlogPostEntity.builder()
            .title("리액트의 조건부 렌더링이란?")
            .content(
                "안녕하세요, 소플입니다.\\n이번 글에서는 리액트의 조건부 렌더링에 대해서 배워보도록 하겠습니다.\\n조건부 렌더링은 말 그대로 조건에 따라서 렌더링을 다르게 한다는 의미입니다.")
            .comments(Arrays.asList(
                BlogPostCommentEntity.builder()
                    .content("이렇게 사용하는 방법이 있었군요!")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("좋은 글 감사합니다ㅎㅎ")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("항상 ?만 사용했었는데, 이제 &&도 사용해봐야 겠네요.")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("쉬운 설명 감사드립니다")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("바로 코드에 적용해보겠습니다!!")
                    .build()))
            .build();

        BlogPostEntity post2 = BlogPostEntity.builder()
            .title("리액트 Hook에 대해서 배워볼까요?")
            .content(
                "안녕하세요, 소플입니다.\\n이번 글에서는 리액트의 Hook에 대해서 배워보도록 하겠습니다.\\nHook은 리액트의 함수 컴포넌트의 흐름에 끼어들어서 다양한 작업들을 처리하기 위해서 사용합니다.")
            .comments(Arrays.asList(
                BlogPostCommentEntity.builder()
                    .content("어려운 개념이었는데, 글을 읽고 조금 정리가 된 것 같습니다.")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("Hook이 뭔가 했더니 이런거였군요. 알려주셔서 감사합니다ㅎㅎ")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("처음에 훅을 접했을 때 너무 어려웠는데 감사합니다!")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("앞으로는 잘 사용할 수 있을것 같아요")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("이름부터 너무 어려운 훅...")
                    .build()))
            .build();

        BlogPostEntity post3 = BlogPostEntity.builder()
            .title("리액트 컴포넌트 개념 소개")
            .content(
                "이번 글에서는 리액트의 컴포넌트에 대해서 설명을 해보려고 합니다.\\n리액트가 컴포넌트 기반이라는 것은 리액트를 조금만 공부해보신 분들도 다 알고 계실겁니다.\\n그렇다면 컴포넌트는 도대체 뭘까요?")
            .comments(Arrays.asList(
                BlogPostCommentEntity.builder()
                    .content("헷갈렸던 개념을 확실히 이해할 수 있어서 좋네요ㅋㅋ")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("컴포넌트에 대한 쉬운 설명 감사드려요")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("컴포넌트를 제대로 이해하지 않은 상태로 사용하기만 했는데 확실히 개념을 잡을 수 있어서 좋습니다!")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("리액트는 컴포넌트 기반이라서 재사용성도 높고 정말 좋은것 같아요")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("리액트 최고!!")
                    .build()))
            .build();

        BlogPostEntity post4 = BlogPostEntity.builder()
            .title("처음 만난 리액트 강의 소개")
            .content(
                "안녕하세요, 소플입니다.\\n오늘은 제가 만든 리액트 강의를 소개해드리려고 합니다.\\n강의 이름은 '처음 만난 리액트'입니다.\\n강의 이름에서 이미 느끼셨을텐데, 리액트 초보자분들을 위한 강의입니다.")
            .comments(Arrays.asList(
                BlogPostCommentEntity.builder()
                    .content("강의 너무 좋아요~!")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("초보자도 쉽게 이해할 수 있어서 좋습니다")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("실습도 따라하면서 하는데 좋아요")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("좋은 강의 감사드립니다")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("오 이런 강의가 있었군요~")
                    .build()))
            .build();

        BlogPostEntity post5 = BlogPostEntity.builder()
            .title("안녕하세요 소플입니다.")
            .content(
                "제 블로그에 오신 것을 환영합니다.\\n앞으로 유익한 글들을 자주 올리도록 하겠습니다!")
            .comments(Arrays.asList(
                BlogPostCommentEntity.builder()
                    .content("많이 올려주세요!")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("와 좋습니다ㅎㅎ")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("리액트 너무 어려워요ㅠㅠ")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("소플님 강의 잘 듣고 있습니다~!")
                    .build(),
                BlogPostCommentEntity.builder()
                    .content("꾸준히 블로그 활동 해주세요!!")
                    .build()))
            .build();

        post1.setRelation();
        post2.setRelation();
        post3.setRelation();
        post4.setRelation();
        post5.setRelation();
        blogPostRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5));
    }
}
