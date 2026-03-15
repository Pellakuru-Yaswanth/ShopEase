import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    items: [],
    totalQuantity: 0,
    totalAmount: 0,
    loading: false,
};

const cartSlice = createSlice({
    name: 'cart',
    initialState,
    reducers: {
        // Replace entire cart (useful when fetching from backend on login)
        setCart: (state, action) => {
            state.items = action.payload.cartItems; // Assuming your API returns 'cartItems'
            state.totalQuantity = action.payload.cartItems.reduce((acc, item) => acc + item.quantity, 0);
            state.totalAmount = action.payload.cartItems.reduce((acc, item) => acc + (item.product.price * item.quantity), 0);
        },
        
        addItem: (state, action) => {
            const newItem = action.payload;
            const existingItem = state.items.find(item => item.product.productId === newItem.product.productId);
            
            state.totalQuantity++;
            state.totalAmount += newItem.product.price;

            if (!existingItem) {
                state.items.push({
                    product: newItem.product,
                    quantity: 1,
                    totalPrice: newItem.product.price
                });
            } else {
                existingItem.quantity++;
                existingItem.totalPrice += newItem.product.price;
            }
        },

        removeItem: (state, action) => {
            const id = action.payload;
            const existingItem = state.items.find(item => item.product.productId === id);
            
            if (existingItem) {
                state.totalQuantity--;
                state.totalAmount -= existingItem.product.price;

                if (existingItem.quantity === 1) {
                    state.items = state.items.filter(item => item.product.productId !== id);
                } else {
                    existingItem.quantity--;
                    existingItem.totalPrice -= existingItem.product.price;
                }
            }
        },

        clearCart: (state) => {
            state.items = [];
            state.totalQuantity = 0;
            state.totalAmount = 0;
        }
    }
});

export const { setCart, addItem, removeItem, clearCart } = cartSlice.actions;
export default cartSlice.reducer;