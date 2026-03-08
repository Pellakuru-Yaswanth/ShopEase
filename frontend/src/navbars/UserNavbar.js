import '../css_styles/UserNavbar.css';
import { useState, useEffect, useRef } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux"; // Added useSelector for cart logic
import axios from 'axios';
import { logout, clearCart } from "../redux/store";

export default function UserNavbar() {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const location = useLocation();
    
    // Replace '0' with your actual Redux state: useSelector(state => state.cart.totalItems)
    const cartCount = useSelector(state => state.cart.cartSize);
    const userId = useSelector(state => state.user.userInfo.userId);

    const [showDropdown, setShowDropdown] = useState(false);
    const dropdownRef = useRef(null);

    const isActive = (path) => location.pathname === path ? 'active' : '';

    const handleLogout = () => {
        dispatch(logout());
        dispatch(clearCart());
        navigate('/login');
    };

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setShowDropdown(false);
            }
        };
        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, []);

    return (
        <nav id="shopease-nav">
            <div className="nav-left">
                <div className="nav-logo" onClick={() => navigate('/')}>ShopEase</div>
                <a className={isActive('/')} onClick={() => navigate('/')}>Home</a>
                <a className={isActive('/showItems')} onClick={() => navigate('/showItems')}>Shop</a>
            </div>

            <div className="nav-center">
                <div className="search-container">
                    <input type="text" placeholder="Search for products..." />
                    <button className="search-icon-btn">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5">
                            <circle cx="11" cy="11" r="8"></circle>
                            <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
                        </svg>
                    </button>
                </div>
            </div>

            <div className="nav-right" ref={dropdownRef}>
                {/* NEW: Cart Icon with Badge */}
                <div className="nav-cart" onClick={() => navigate('/cart')}>
                    <div className="cart-icon-wrapper">
                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                            <circle cx="9" cy="21" r="1"></circle>
                            <circle cx="20" cy="21" r="1"></circle>
                            <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
                        </svg>
                        {<span className="cart-badge">{cartCount}</span>}
                    </div>
                </div>

                <div 
                    className={`profile-trigger ${showDropdown ? 'active' : ''}`}
                    onClick={() => setShowDropdown(!showDropdown)}
                >
                    <div className="profile-text">
                        <span>Hello, User</span>
                        <strong>Account</strong>
                    </div>
                    <div className="avatar-box">
                        <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5">
                            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                            <circle cx="12" cy="7" r="4"></circle>
                        </svg>
                    </div>
                </div>

                {showDropdown && (
                    <div className="profile-dropdown">
                        <div className="dropdown-item" onClick={() => { navigate('/profile'); setShowDropdown(false); }}>Your Profile</div>
                        <div className="dropdown-item" onClick={() => { navigate('/orders'); setShowDropdown(false); }}>Your Orders</div>
                        <div className="dropdown-divider"></div>
                        <div className="dropdown-item logout-link" onClick={handleLogout}>Sign Out</div>
                    </div>
                )}
            </div>
        </nav>
    );
}