import { configureStore } from '@reduxjs/toolkit';
import userReducer from './userSlice';
import cartReducer from './cartSlice';

export const store = configureStore({
    reducer: {
        user: userReducer,
        cart: cartReducer
        // You can add cart: cartReducer here later
    },
});