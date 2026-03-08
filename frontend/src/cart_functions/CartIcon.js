import { useState } from "react";
import '../css_styles/CartIcon.css';

function CartIcon() {
    const [itemCount, setItemCount] = useState(0);
    return (
        <div id="cart">
            <div id="cartLogo">

            </div>
            <div id="cartCount">
                {itemCount}
            </div>
        </div>
    );
}
export default CartIcon;