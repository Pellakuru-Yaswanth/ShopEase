import { useDispatch } from "react-redux";

import { addItem, removeItem } from "../redux/store";

export default function CartItems() {

    const dispatch = useDispatch();

    const addItems = (val) => {
        for(let i=0; i<val; i++){
            const cartItem = {
                itemId: i+1,
                itemName: "Apple"+(i+1),
                quantity: 5
            }
        }
    }

    return(
        <div id="cartItems">
            <button onClick={() => addItems(5)}>addItem</button>
        </div>
    );
}