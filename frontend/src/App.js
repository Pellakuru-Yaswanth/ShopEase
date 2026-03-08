import './App.css';
import { Routes, Route } from 'react-router-dom';

import NavbarManager from './navbars/NavbarManager';

import Home from './main_components/Home';

import ChangePassword from './user_functions/ChangePassword';
import Login from './user_functions/Login';
import Profile from './user_functions/Profile';
import Signup from './user_functions/Signup'; 

import CreateOrEditItem from './item_functions/CreateOrEditItem';
import ShowItems from './item_functions/ShowItems';

import CartItems from './cart_functions/CartItems';
import DebitOrCreditCardPayment from './cart_functions/DebitOrCreditCardPayment';
import OrderStatus from './cart_functions/OrderStatus';
import OrderSummary from './cart_functions/OrderSummary';
import PaymentSelection from './cart_functions/PaymentSelection';

function App() {

  return (
    <Routes>
      <Route path='/' element={<Home/>}/>
      <Route path='/login' element={<Login/>}/>
      <Route path='/changePassword' element={<ChangePassword/>}/>
      <Route path='/profile' element={<Profile/>}/>
      <Route path='/signup' element={<Signup/>}/>
      
      <Route path='/createItem' element={<CreateOrEditItem/>}/>
      <Route path='/editItem' element={<CreateOrEditItem/>}/>
      <Route path='/showItems' element={<ShowItems/>}/>
      
      <Route path='/cardPayment' element={<DebitOrCreditCardPayment/>}/>
      <Route path='/cartItems' element={<CartItems/>}/>
      <Route path='/orderStatus' element={<OrderStatus/>}/>
      <Route path='/orderSummary' element={<OrderSummary/>}/>
      <Route path='/paymentSelection' element={<PaymentSelection/>}/>
    </Routes>
  );
}

export default App;
