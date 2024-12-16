import styled from "styled-components";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import TextInput from "../ui/TextInput";
import Button from "../ui/Button";
import {useMutation, useQueryClient} from "@tanstack/react-query";

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

function PostWritePage(props) {
  const navigate = useNavigate();
  const baseUrl = process.env.REACT_APP_BLOG_POST_BASE_URL

  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const queryClient = useQueryClient();

  const register = useMutation({
    mutationFn: async (requestData) => {
      const response = await axios.post(baseUrl, requestData, {
        timeout: 10000,
        headers: {
          'Content-Type': 'application/json',
        }
      })
      return response.data;
    },
    onSuccess: (data) => {
      queryClient.invalidateQueries({queryKey: ['postList']}) // 캐시 무효화 (다음 호출시 서버에서 데이터 갱신)
      navigate(`/posts/${data.id}`)
    },
    onError: (error) => {
      console.log(error)
      alert(error.message)
    },
    retry: 3,
    retryDelay: 500
  })

  return (
      <Wrapper>
        <Container>
          <TextInput
              height={20}
              value={title}
              onChange={(event) => {
                setTitle(event.target.value)
              }}
          />

          <TextInput
              height={480}
              value={content}
              onChange={(event) => {
                setContent(event.target.value)
              }}
          />

          <Button
              title="등록"
              onClick={() => register.mutate({title: title, content: content})}
          />
        </Container>
      </Wrapper>
  )
}

export default PostWritePage;