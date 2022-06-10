import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Container } from "./style";
import logo from "../../img/nanum_logo.png";
import {
  createSignUpVerificationCode,
  confirmSignUpVerificationCode,
} from "../../api/verifyAPI";
import { signup } from "../../api/userAPI";
import { Box, CircularProgress } from "@mui/material";

const SignUp = () => {
  const navigate = useNavigate();

  const [input, setInput] = useState({
    username: "",
    verificationCode: "",
    nickname: "",
    password: "",
    passwordConfirm: "",
  });
  const [loading, setLoading] = useState(false);

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setInput({ ...input, [name]: value });
  };

  const handleSendCode = async (e) => {
    e.preventDefault();
    setLoading(true);
    const emailRegex = /@(gm.)?gist.ac.kr$/;
    if (!emailRegex.test(input.username)) {
      setMessage("지스트 메일을 이용해주세요");
      return;
    }

    const response = await createSignUpVerificationCode({
      username: input.username,
    });
    const status = response.status;

    if (status < 400) {
      setLoading(false);

      setMessage("인증번호가 전송되었습니다.");
    } else {
      console.log(response);
      console.log(status);
    }
  };

  const handleVerify = async (e) => {
    e.preventDefault();
    try {
      const response = await confirmSignUpVerificationCode({
        username: input.username,
        verificationCode: input.verificationCode,
      });
      const status = response.status;
      if (status < 400) {
        setMessage("인증되었습니다.");
      }
    } catch {
      setMessage("인증번호를 정확히 입력해주세요.");
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (input.password === input.passwordConfirm) {
      const response = await signup({
        username: input.username,
        verificationCode: input.verificationCode,
        nickname: input.nickname,
        password: input.password,
      });
      const status = response.status;

      if (status < 400) {
        navigate("/signin");
      } else {
        console.log(response);
        console.log(status);
      }
    } else {
      setMessage("비밀번호가 일치하지 않습니다.");
    }
  };

  return (
    <Container className="signup-container">
      <img className="nanum-logo" src={logo} alt="nanum_logo" />
      <form onSubmit={handleSubmit}>
        <div className="wrapper-in-form">
          <div className="input-wrapper email-input">
            <input
              name="username"
              type="username"
              placeholder="지스트 이메일"
              onChange={handleChange}
            ></input>
            <button
              className="verification-btn"
              type="button"
              onClick={handleSendCode}
            >
              전송
            </button>
          </div>
          <div className="input-wrapper email-input">
            <input
              name="verificationCode"
              value={input.verificationCode}
              placeholder="인증코드"
              onChange={handleChange}
            ></input>
            <button
              className="verification-btn"
              type="button"
              onClick={handleVerify}
            >
              인증
            </button>
          </div>
          <div className="input-wrapper verification-code-input">
            <input
              name="nickname"
              value={input.nickname}
              placeholder="닉네임"
              onChange={handleChange}
            ></input>
          </div>

          <div className="input-wrapper password-input">
            <input
              name="password"
              value={input.password}
              type="password"
              placeholder="비밀번호"
              onChange={handleChange}
            ></input>
          </div>
          <div className="input-wrapper confirm-password-input">
            <input
              name="passwordConfirm"
              value={input.passwordConfirm}
              type="password"
              placeholder="비밀번호 확인"
              onChange={handleChange}
            ></input>
          </div>
          <div className="btn-wrapper">
            <button className="signup-btn">회원가입</button>
          </div>
          <div>
            <span>
              이미 계정이 있으신가요? <Link to="/signin">로그인</Link>
            </span>
          </div>
          {loading && (
            <div className="loading-spinner">
              <Box sx={{ display: "flex" }}>
                <CircularProgress />
              </Box>
              <div>잠시만 기다려주세요...</div>
            </div>
          )}
          <div>
            <span className="error-msg">{message}</span>
          </div>
        </div>
      </form>
    </Container>
  );
};

export default SignUp;
