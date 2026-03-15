import { createSlice } from '@reduxjs/toolkit';

// Check if user is already saved in local storage to persist session
const savedUser = JSON.parse(localStorage.getItem('user'));

const initialState = {
    userInfo: savedUser ? savedUser : null,
    isLoggedIn: !!savedUser,
    loading: false,
    error: null,
};

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        loginStart: (state) => {
            state.loading = true;
            state.error = null;
        },
        loginSuccess: (state, action) => {
            state.loading = false;
            state.isLoggedIn = true;
            state.userInfo = action.payload;
            state.error = null;
            // Persist session
            localStorage.setItem('user', JSON.stringify(action.payload));
        },
        loginFailure: (state, action) => {
            state.loading = false;
            state.isLoggedIn = false;
            state.error = action.payload;
        },
        logout: (state) => {
            state.userInfo = null;
            state.isLoggedIn = false;
            state.loading = false;
            state.error = null;
            localStorage.removeItem('user');
        }
    }
});

export const { loginStart, loginSuccess, loginFailure, logout } = userSlice.actions;
export default userSlice.reducer;