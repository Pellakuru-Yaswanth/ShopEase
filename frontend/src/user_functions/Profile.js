import '../css_styles/Profile.css';

import { useSelector } from "react-redux";

export default function Profile() {

    const user = useSelector((state) => state.user);
    console.log("UserState is "+user.isLoggedIn+" "+user.userInfo);

    return (
        <div id="profile-container">
            <div id="profile-card">
                <div className="profile-header">
                    <div className="avatar-circle">
                        {/* Placeholder for User Initials or Image */}
                        <span>JD</span>
                    </div>
                    <h2>User Profile</h2>
                    <p className="user-role">Premium Member</p>
                </div>

                <div className="profile-details">
                    <div className="detail-group">
                        <label>Full Name</label>
                        <p className="detail-value">John Doe</p>
                    </div>

                    <div className="detail-group">
                        <label>Email ID</label>
                        <p className="detail-value">johndoe@example.com</p>
                    </div>

                    <div className="detail-group">
                        <label>Phone Number</label>
                        <p className="detail-value">+91 9876543210</p>
                    </div>

                    <div className="detail-group">
                        <label>User ID</label>
                        <p className="detail-value">USR88291</p>
                    </div>
                </div>

                <div className="profile-actions">
                    <button className="edit-btn">Edit Profile</button>
                    <button className="logout-btn">Logout</button>
                </div>
            </div>
        </div>
    );
}