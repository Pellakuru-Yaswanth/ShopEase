import { useSelector } from "react-redux";

import MainNavbar from "./MainNavbar";
import UserNavbar from "./UserNavbar";

export default function NavbarManager() {

    const userState = useSelector((state) => state.user.isLoggedIn);

    return (
        <div id="navbarManager">
            {userState ? <UserNavbar/> : <MainNavbar/>}
        </div>
    );
}