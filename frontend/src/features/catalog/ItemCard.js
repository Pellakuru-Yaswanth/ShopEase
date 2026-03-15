import { useSelector, useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { addItem, removeItem } from "../redux_store/cartSlice";
import '../../styles/catalog/ItemCard.css';

export default function ItemCard({item}){
    console.log(item);
    
    const isLoggedIn = useSelector((state) => state.user.isLoggedIn);
    const cartItems = useSelector((state) => state.cart.items);

    const dispatch = useDispatch();
    const navigate = useNavigate();

    // Helper: Find how many of this item are in the cart
    const getItemQuantity = (id) => {
        const item = cartItems.find(i => i.product.productId === id);
        return item ? item.quantity : 0;
    };

    const quantity = getItemQuantity(item.productId);
    // Cart Actions
    const handleIncrease = (item) => {
        if (isLoggedIn) {
            dispatch(addItem({ product: item }));
        } else {
            navigate('/login');
        }
    };

    const handleDecrease = (id) => {
        dispatch(removeItem(id));
    };

    return (
        <div className="item-card" key={item.productId}>
            {item.isNewlyLaunched && <div className="badge">New</div>}
            
            <div className="item-image-placeholder">
                <span>{item.itemImage}</span>
            </div>

            <div className="item-details">
                <span className="category-tag">{item.name}</span>
                <h3>{item.name}</h3>
                
                <div className="price-row">
                    <span className="current-price">${item.price.toFixed(2)}</span>
                    {item.oldPrice > 0 && (
                        <span className="old-price">${item.oldPrice.toFixed(2)}</span>
                    )}
                </div>

                {/* Dynamic Cart Controls */}
                <div className="cart-controls">
                    {quantity === 0 ? (
                        <button 
                            className="add-btn" 
                            onClick={() => handleIncrease(item)}
                        >
                            Add to Cart
                        </button>
                    ) : (
                        <div className="quantity-selector">
                            <button onClick={() => handleDecrease(item.productId)}>−</button>
                            <span className="qty-count">{quantity}</span>
                            <button onClick={() => handleIncrease(item)}>+</button>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}