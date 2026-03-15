import React, { useState } from 'react';
import '../../styles/identity_and_auth/LoginForm.css'; // Reusing the same CSS file for consistency
import { useNavigate } from 'react-router-dom';

const RegisterForm = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        userId: 1,
        email: '',
        password: '',
        firstName: '',
        lastName: '',
        phoneNumber: '',
        isAdmin: false
    });

    const [error, setError] = useState('');
    const [success, setSuccess] = useState(false);
    const [isLoading, setIsLoading] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    function isNumber(val) {
        // 1. null, undefined, and empty strings return false
        if (val === null || val === undefined || val === '') return false;

        // 2. isNaN(val) converts the value to a number internally
        // 3. isFinite(val) ensures it isn't Infinity or -Infinity
        console.log(Number(val));
        return !isNaN(Number(val)) && isFinite(val);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setIsLoading(true);
        try {
            if(!isNumber(formData.phoneNumber)) {
                setError('Phone Number should contain digits only');
                return;
            }
            const response = await fetch('http://localhost:8081/api/users/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                setSuccess(true);
                setFormData({ userId: 1, firstName: '', lastName: '', email: '', password: '', phoneNumber: '', isAdmin: false });
            } else {
                const message = await response.text();
                setError(message || 'Registration failed. Try again.');
            }
        } catch (err) {
            setError('Could not connect to the server.');
        } finally {
            setIsLoading(false);
        }
    };

    if (success) {
        return (
            <div className="login-container">
                <div className="login-form">
                    <h2>Success!</h2>
                    <p>Account created successfully. You can now login.</p>
                    <button className="login-button" onClick={() => navigate('/login')}>
                        Go to Login
                    </button>
                </div>
            </div>
        );
    }

    return (
        <div className="login-container">
            <form className="login-form" onSubmit={handleSubmit}>
                <h2>Create Account</h2>
                
                {error && <div className="error-message">{error}</div>}

                <div className="form-group">
                    <label htmlFor="firstName">First Name</label>
                    <input
                        id="firstName"
                        type="text"
                        name="firstName"
                        value={formData.firstName}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="lastName">Last Name</label>
                    <input
                        id="lastName"
                        type="text"
                        name="lastName"
                        value={formData.lastName}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="email">Email Address</label>
                    <input
                        id="email"
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="phone">Phone Number</label>
                    <input
                        id="phoneNumber"
                        type="tel"
                        name="phoneNumber"
                        value={formData.phoneNumber}
                        onChange={handleChange}
                        maxLength={10}
                        minLength={10}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                        id="password"
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        minLength={8}
                        required
                    />
                </div>

                <button type="submit" className="login-button" disabled={isLoading}>
                    {isLoading ? 'Creating Account...' : 'Sign Up'}
                </button>

                <p style={{ textAlign: 'center', marginTop: '1rem', fontSize: '0.9rem' }}>
                    Already have an account? <a href="/login">Login here</a>
                </p>
            </form>
        </div>
    );
};

export default RegisterForm;