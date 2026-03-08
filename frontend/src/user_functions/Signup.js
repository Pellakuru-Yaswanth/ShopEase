import '../css_styles/Signup.css';
export default function Signup() {
    const user = {
        userId: 0,
        fullName: "",
        password: "",
        countryCode: "",
        phoneNumber: "",
        email: ""
    }

    const errors = {
        name: "",
        email: "",
        phone: "",
        password: "",
        confirmPassword: ""
    }

    const handleSignup = (e) => {

    }

    return (
        <div id="signup">
            <form onSubmit={handleSignup}>
                <h2>Create Account</h2>
                
                <label>Full Name</label>
                <input type="text" placeholder="Enter your name" name="name" required />
                <p className="error">{errors.name}</p>

                <label>Email ID</label>
                <input type="email" placeholder="email@example.com" name="email" required />
                <p className="error">{errors.email}</p>

                <div className="phone-container">
                    <div className="field-group">
                        <label>Code</label>
                        <select name="countryCode">
                            <option value="+91">+91 (IN)</option>
                            <option value="+1">+1 (US)</option>
                            <option value="+44">+44 (UK)</option>
                            <option value="+971">+971 (UAE)</option>
                        </select>
                    </div>
                    <div className="field-group phone-input">
                        <label>Phone Number</label>
                        <input type="tel" placeholder="1234567890" name="phoneNumber" required />
                    </div>
                </div>
                <p className="error">{errors.phone}</p>

                <label>Password</label>
                <input type="password" placeholder="Min 8 characters" name="password" minLength={8} required />
                <p className="error">{errors.password}</p>

                <label>Confirm Password</label>
                <input type="password" placeholder="Re-enter password" name="confirmPassword" required />
                <p className="error">{errors.confirmPassword}</p>

                <input type="submit" value="Sign Up" />
            </form>
            <p>Already have an account? <a href="/login">Login</a></p>
        </div>
    );
}