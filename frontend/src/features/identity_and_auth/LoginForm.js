import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

import '../../styles/identity_and_auth/LoginForm.css';
import { loginStart, loginSuccess, loginFailure, isLoading } from '../redux_store/userSlice';

const LoginForm = () => {

    const [credentials, setCredentials] = useState({ email: '', password: '' });
    
    // Redux Hooks
    const dispatch = useDispatch();
    const { loading, error } = useSelector((state) => state.user);
    const navigate = useNavigate();

    const handleChange = (e) => {
        setCredentials({ ...credentials, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        dispatch(loginStart());

        try {
            const response = await fetch('http://localhost:8081/api/users/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(credentials),
            });

            if (response.ok) {
                const data = await response.json();
                dispatch(loginSuccess(data));
                navigate('/');
            } else {
                const msg = await response.text();
                dispatch(loginFailure(msg));
            }
        } catch (err) {
            dispatch(loginFailure('Server Error'));
        }
    };

    return (
        <div className="login-container">
            <form className="login-form" onSubmit={handleSubmit}>
                <h2>Login</h2>
                
                {error && <div className="error-message">{error}</div>}

                <div className="form-group">
                    <label htmlFor="email">Email Address</label>
                    <input
                        id="email"
                        type="email"
                        name="email"
                        placeholder="Enter your email"
                        value={credentials.email}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                        id="password"
                        type="password"
                        name="password"
                        placeholder="Enter your password"
                        value={credentials.password}
                        onChange={handleChange}
                        minLength={8}
                        required
                    />
                </div>

                <button type="submit" className="login-button" disabled={loading}>
                    {loading ? 'Logging in...' : 'Login'}
                </button>

                <p style={{ textAlign: 'center', marginTop: '1rem', fontSize: '0.9rem' }}>
                    Don't have an account? <a href="/register">Register here</a>
                </p>
            </form>
        </div>
    );
};

export default LoginForm;