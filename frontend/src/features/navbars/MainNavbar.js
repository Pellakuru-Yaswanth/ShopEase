import '../../styles/navbars/MainNavbar.css';

import { useNavigate, useLocation } from "react-router-dom";
import SearchBar from '../catalog/SearchBar';

export default function MainNavbar() {

    const navigate = useNavigate();

    const location = useLocation();

    const isActive = (path) => location.pathname === path ? 'active' : '';

    return (
        <nav id="mainNavbar">
            {/* LEFT: Navigation Links */}
            <div className="nav-links">
                <a className={isActive('/')} onClick={() => navigate('/')}>Home</a>
                <a className={isActive('/shop')} onClick={() => navigate('/shop')}>Shop</a>
                <a className={isActive('/login')} onClick={() => navigate('/login')}>Login</a>
            </div>

            {/* RIGHT: Flexible Search Bar */}
            <div className="nav-search">
                <SearchBar/>
            </div>
        </nav>
    );
}