import '../css_styles/MainNavbar.css';

import { useNavigate, useLocation } from "react-router-dom";

export default function MainNavbar() {

    const navigate = useNavigate();

    const location = useLocation();

    const isActive = (path) => location.pathname === path ? 'active' : '';

    return (
        <nav id="mainNavbar">
            {/* LEFT: Navigation Links */}
            <div className="nav-links">
                <a className={isActive('/')} onClick={() => navigate('/')}>Home</a>
                <a className={isActive('/showItems')} onClick={() => navigate('/showItems')}>Shop</a>
                <a className={isActive('/login')} onClick={() => navigate('/login')}>Login</a>
            </div>

            {/* RIGHT: Flexible Search Bar */}
            <div className="nav-search">
                <div className="search-wrapper">
                    <input 
                        type="text" 
                        placeholder="Search ShopEase..." 
                        aria-label="Search"
                    />
                    <button className="search-submit">
                        <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="3">
                            <circle cx="11" cy="11" r="8"></circle>
                            <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
                        </svg>
                    </button>
                </div>
            </div>
        </nav>
    );
}