import styled from "styled-components";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import {useState} from "react";
import TextInput from "../ui/TextInput";
import Button from "../ui/Button";
import {useMutation, useQuery, useQueryClient} from "@tanstack/react-query";
import CommentList from "../list/CommentList";

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

const PostContainer = styled.div`
  padding: 8px 16px;
  border: 1px solid gray;
  border-radius: 8px;
  display: ${props => (props.hide === undefined ? "block" : props.hide === 'Y' ? "none" : "block")};
`

const TitleText = styled.p`
  font-size: 20px;
  font-weight: 500;
`

const ContentText = styled.p`
  font-size: 20px;
  line-height: 32px;
  white-space: pre-wrap;
`

const CommentLabel = styled.p `
  font-size: 16px;
  font-weight: 500;
`

function PostViewPage(props) {
  const navigate = useNavigate();
  const queryClient = useQueryClient();

  const postId = useParams().id
  const baseUrl = process.env.REACT_APP_BLOG_POST_BASE_URL

  const [newComment, setNewComment] = useState('');
  const [isUpdatePostTypeA, setUpdatePostTypeA] = useState('N');
  const [isUpdatePostTypeB, setUpdatePostTypeB] = useState('Y');

  const [newTitle, setNewTitle] = useState('');
  const [newContent, setNewContent] = useState('');

  const {isLoading, isError, data, error} = useQuery({
    queryKey: ['post', postId],
    queryFn: async () => {
      const response = await axios.get(baseUrl + `/${postId}`);
      return response.data
    },
    staleTime: 100000,
    cacheTime: 100000,
    retry: 3,
    retryDelay: 1000,
    keepPreviousData: true
  });

  const deletePost = useMutation({
    mutationFn: async async => {
      const response = await axios.delete(  baseUrl + `/${postId}`)
      return response.data;
    },
    onSuccess: () => {
      // 캐시 무효화 (다음 호출시 서버에서 데이터 갱신)
      queryClient.invalidateQueries({queryKey: ['postList']})
      navigate('/')
    },
    onError: (error) => {
      console.log(error)
      alert(error.message)
    },
    retry: 3,
    retryDelay: 500
  })

  const updatePost = useMutation({
    mutationFn: async (requestData) => {
      const response = await axios.put(baseUrl + `/${postId}`, requestData, {
        timeout: 10000,
        headers: {
          'Content-Type': 'application/json',
        },
      })
      return response.data
    },
    onSuccess: (response) => {
      queryClient.invalidateQueries({queryKey: ['postList']})

      // cache update
      queryClient.setQueryData(['post', postId], response)
      setUpdatePostTypeA('N')
      setUpdatePostTypeB('Y')
    },
    onError: (error) => {
      console.log(error)
      alert(error.message)
    },
    retry: 3,
    retryDelay: 500
  })

  const registerComment = useMutation({
    mutationFn: async (requestData) => {
      const response = await axios.post(baseUrl + `/${postId}/comments`, requestData,{
        timeout: 10000,
        headers: {
          'Content-Type': 'application/json',
        },
      });
      return response.data
    },
    onSuccess: (response) => {
      // cache update
      queryClient.setQueryData(['post', postId], (oldData) => {
        if (!oldData) return;
        return {
          ...oldData,
          comments: [...oldData.comments, response],
        };
      });

      setNewComment('')
    },
    onError: (error) => {
      console.log(error)
    },
    retry: 3,
    retryDelay: 500
  })

  const showUpdatePostView = () =>{
    setNewTitle(data.title);
    setNewContent(data.content);
    setUpdatePostTypeA('Y');
    setUpdatePostTypeB('N');
  }

  const hideUpdatePostView = () => {
    setUpdatePostTypeA('N');
    setUpdatePostTypeB('Y');
  }

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
              title="목록으로 이동"
              onClick={() => navigate("/")}
          />
          <PostContainer hide={isUpdatePostTypeA}>
            <TitleText>{data.title}</TitleText>
            <ContentText>{data.content}</ContentText>
          </PostContainer>
          <Button
              title="수정하기"
              onClick={() => showUpdatePostView()}
              $hide={isUpdatePostTypeA}
          />
          <Button
              title="삭제하기"
              onClick={() => deletePost.mutate()}
              $hide={isUpdatePostTypeA}
          />

          {/* ----------- post update view ----------- */}
          <PostContainer hide={isUpdatePostTypeB}>
            <TextInput
                height={10}
                value={newTitle}
                onChange={(event) => {
                  setNewTitle(event.target.value)
                }}
            />
            <TextInput
                height={150}
                value={newContent}
                onChange={(event) => {
                  setNewContent(event.target.value)
                }}
            />
          </PostContainer>
          <Button
              title="수정 완료"
              onClick={() => updatePost.mutate({title: newTitle, content: newContent})}
              hide={isUpdatePostTypeB}
          />
          <Button
              title="수정 취소"
              onClick={() => hideUpdatePostView()}
              hide={isUpdatePostTypeB}
          />
          {/* ----------- post update view ----------- */}



          <CommentLabel>댓글</CommentLabel>
          <TextInput
              height={20}
              value={newComment}
              onChange={(event) => {
                setNewComment(event.target.value)
              }}
          />
          <Button
              title="댓글 등록"
              onClick={() => registerComment.mutate({content: newComment})}
          />
          <CommentList comments={data.comments} />
        </Container>
      </Wrapper>
  )
}

export default PostViewPage;