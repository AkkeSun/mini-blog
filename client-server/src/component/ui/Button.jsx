import styled from 'styled-components';

const StyledButton = styled.button`
  padding: 8px 16px;
  font-size: 16px;
  border-width: 1px;
  border-radius: 8px;
  cursor: pointer;
  margin-bottom: 20px;
  display: ${props => (props.hide === undefined ? "inline-block" : props.hide === 'Y' ? "none" : "inline-block")};
`

function Button(props) {
  return (
      <StyledButton onClick={props.onClick} disabled={props.disabled} hide={props.hide}>
        {props.title}
      </StyledButton>
  );
}

export default Button;