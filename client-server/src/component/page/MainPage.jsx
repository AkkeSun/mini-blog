import styled from 'styled-components';
import axios from "axios";
import Button from "../ui/Button";
import {useState} from "react";
import PostList from "../list/PostList";
import {useQuery} from "@tanstack/react-query";
import {useNavigate} from "react-router-dom";

const Wrapper = styled.div`
  padding: 16px;
  width: calc(100% - 32px);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`

const Container = styled.div`
  width: 100%;
  max-width: 720px;

  & > * {
    :not(:last-child) {
      margin-bottom: 16px;
    }
  }
`

function MainPage(props) {
  const [nowPage, setNowPage] = useState(0);

  const baseUrl = process.env.REACT_APP_BLOG_POST_BASE_URL
  const navigate = useNavigate();

  const {isLoading, isError, data, error} = useQuery({
    queryKey: ['postList', nowPage], // cache key
    queryFn: async (signal) => { // cache data
      const response = await axios.get(baseUrl + `?page=${nowPage}`)
      return response.data;
    },
    staleTime: 60000,  // 60초 동안 캐시 데이터 응답
    cacheTime: 60000, // 캐시데이터가 메모리에 유지되는 시간
    retry: 3,          // 실패 시 3번까지 자동 재시도
    retryDelay: 1000,  // 재시도 간 1초 대기
    keepPreviousData: true // 이전 데이터 유지한채 세로운 데이터 요청
  });


  if (isLoading) {
    return (
        <Wrapper>Loading...</Wrapper>
    )
  }

  if (isError) {
    return (
        <Wrapper>Error: {error.message}</Wrapper>
    )
  }

  return (
      <Wrapper>
        <Container>
          <Button
              title="글 작성하기"
              onClick={() => navigate("/post-write")}
          />
          <PostList
              posts={data.posts}
              onClick={(id) => navigate(`/posts/${id}`)}
          />
          <Button title="prePage"
                  onClick={() => setNowPage(nowPage - 1)}
                  disabled={nowPage === 0}
          />
          <Button title="nextPage"
                  onClick={() => setNowPage(nowPage + 1)}
                  disabled={data.totalPage === nowPage}
          />
        </Container>
      </Wrapper>
  )
}

export default MainPage;
