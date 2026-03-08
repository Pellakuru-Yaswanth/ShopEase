import '../css_styles/Login.css';
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginSuccess } from "../redux/store";
import { useDispatch } from 'react-redux';

export default function Login() {

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [userIdError, setUserIdError] = useState({state: false, message: ""});
    const [passwordError, setPasswordError] = useState({state: false, message: ""});
    const [otherError, setOtherError] = useState({state: true, message: ""});

    const loadCart = (userId) => {
        axios.get(`http://localhost:8081/cart/getCart/${parseInt(userId)}`).then(res => {
            let data = res.data;
            if(data!=null && data!=''){
             //Loop through data and add to Cart Store
             console.log("Data Present");
            } else console.log("No Items present in cart for the User Id: "+ userId);
        });
    }

    const login = (e) => {
        e.preventDefault();
        //For Temporary test
        dispatch(loginSuccess(({
            userId: 1
        })));
        navigate('/');
        return;
        //Until here temporary test
        setUserIdError(prevState => ({state: false, message: ""}));
        setPasswordError(prevState => ({state: false, message: ""}));
        setOtherError(prevState => ({state: false, message: ""}));
        let userId = e.target.userId.value.trim();
        let password = e.target.password.value.trim();
        if(userId.length==0){
            setUserIdError(prevState => ({state: true, message: "User Id should not be empty"}));
            return;
        }
        if(isNaN(userId)){
            setUserIdError(prevState => ({state: true, message: "User Id should contain digits only"}));
            return;
        }
        if(password.length==0){
            setPasswordError(prevState => ({state: true, message: "Password should not be empty"}));
            return;
        }
        axios.get(`http://localhost:8081/user/login?userId=${parseInt(userId)}&password=${password}`)
            .then(res => {
                let status = res.data;
                console.log(status.message);
                if(status.status==false){
                    if(status.message.includes("Password"))
                        setPasswordError(prevState => ({state: true, message: status.message}));
                    else
                        setUserIdError(prevState => ({state: true, message: status.message}));
                } else {
                    console.log(status.data);
                    dispatch(loginSuccess(status.data));
                    loadCart(userId);
                    navigate('/');
                }
        });
    }

    return (
        <div id="login">
            <form onSubmit={login}>
                <h2>Login</h2>
                <label>User Id: </label>
                <input type="text" placeholder="8 digit User Id" name="userId" minLength={8} maxLength={8}></input>
                <p className="error">{userIdError.message}</p>
                <label>Password: </label>
                <input type="password" placeholder="Enter password" name="password" minLength={8} maxLength={30}></input>
                <p className="error">{passwordError.message}</p>
                <input type="submit" value="Login" disabled={(!userIdError.state && !passwordError.state && !otherError.state)}></input>
            </form>
            <p>Don't have an account? <a href="/signup">Signup</a></p>
        </div>
    );
}