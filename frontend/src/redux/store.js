import { configureStore, createSlice } from "@reduxjs/toolkit";

const userInitialState = {
    userInfo: null,
    isLoggedIn: false
}
const cartInitialState = {
    cartItems: {},
    cartSize: 0,
    totalCartValue: 0
}

const userSlice = createSlice({
    name: 'user',
    initialState: userInitialState,
    reducers: {
        loginSuccess: (state, action) => {
            state.userInfo = action.payload;
            state.isLoggedIn = true;
        },
        logout: (state) => {
            state.userInfo = null;
            state.isLoggedIn = false;
        }
    }
});

const cartSlice = createSlice({
    name: 'cart',
    initialState: cartInitialState,
    reducers: {
        addItem: (state, action) => {
            const item = action.payload;
            const id = item.itemId;
            if(state.cartItems[id]) {
                state.cartItems[id].itemDetails.itemQuantity += 1;
                state.totalCartValue += item.itemCurrentPrice;
                state.cartSize += 1;
            }
            else {
                state.cartItems[id] = {itemDetails: item};
                state.totalCartValue += item.itemCurrentPrice * item.itemQuantity;
                state.cartSize += item.itemQuantity;
            }
        },
        removeItem: (state, action) => {
            const itemId = action.payload;
            state.totalCartValue -= state.cartItems[itemId].itemCurrentPrice;
            if(state.cartItems[itemId].quantity>1) state.cartItems[itemId].itemDetails.itemQuantity -= 1;
            else delete state.cartItems[itemId];
            state.cartSize -= 1;
        },
        clearCart: (state) => {
            state.cartItems = [];
            state.cartSize = 0;
            state.totalCartValue = 0;
        }
    }
});

export const store = configureStore({
    reducer: {
        user: userSlice.reducer,
        cart: cartSlice.reducer
    }
});

export const { loginSuccess, logout } = userSlice.actions;
export const { addItem, removeItem, clearCart } = cartSlice.actions;