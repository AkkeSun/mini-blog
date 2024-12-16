import styled from 'styled-components';

const StyledTextInput = styled.textarea`
  width: calc(100% - 32px);
  ${(props) =>
      props.height ? `height: ${props.height};` : ''
  }
  padding: 16px;
  font-size: 16px;
  line-height: 20px;
`

function TextInput(props) {
  return (
      <StyledTextInput
          height={props.height}
          onChange={props.onChange}
          value={props.value}
          placeholder={props.placeholder}
      />
  );
}

export default TextInput;