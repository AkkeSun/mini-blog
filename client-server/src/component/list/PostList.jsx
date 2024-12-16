import styled from 'styled-components';
import PostListItem from "./PostListItem";

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;

  & > * {
    :not(:last-child) {
      margin-bottom: 16px;
    }
  }
`

function PostList(props) {
  const {posts} = props;

  return (
      <Wrapper>
        {posts.map((post) => (
            <PostListItem
                key={post.id}
                title={post.title}
                onClick={() => props.onClick(post.id)}
            />
        ))}
      </Wrapper>
  )
}

export default PostList;