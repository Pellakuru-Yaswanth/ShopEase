import './App.css';
import { Routes, Route } from 'react-router-dom';

//import NavbarManager from './features/navbars/NavbarManager';

import Home from './features/main_components/Home';

// import ChangePassword from './features/identity_and_auth/ChangePassword';
//import Login from './featuress/identity_and_auth/Login';
import LoginForm from './features/identity_and_auth/LoginForm';
// import Profile from './features/identity_and_auth/Profile';
import RegisterForm from './features/identity_and_auth/RegisterForm';
import Shop from './features/catalog/Shop';
// import CreateOrEditItem from './features/catalog/CreateOrEditItem';
// import ShowItems from './features/catalog/ShowItems';

// import CartItems from './features/shopping/CartItems';
// import DebitOrCreditCardPayment from './features/shopping/DebitOrCreditCardPayment';
// import OrderStatus from './features/shopping/OrderStatus';
// import OrderSummary from './features/shopping/OrderSummary';
// import PaymentSelection from './features/shopping/PaymentSelection';

function App() {

  return (
    <Routes>
      <Route path='/' element={<Home/>}/>
      <Route path='/login' element={<LoginForm/>}/>
      {/* <Route path='/changePassword' element={<ChangePassword/>}/>
      <Route path='/profile' element={<Profile/>}/>*/}
      <Route path='/register' element={<RegisterForm/>}/>
      <Route path='/shop' element={<Shop/>}/>
      {/*<Route path='/createItem' element={<CreateOrEditItem/>}/>
      <Route path='/editItem' element={<CreateOrEditItem/>}/>
      <Route path='/showItems' element={<ShowItems/>}/>
      
      <Route path='/cardPayment' element={<DebitOrCreditCardPayment/>}/>
      <Route path='/cartItems' element={<CartItems/>}/>
      <Route path='/orderStatus' element={<OrderStatus/>}/>
      <Route path='/orderSummary' element={<OrderSummary/>}/>
      <Route path='/paymentSelection' element={<PaymentSelection/>}/> */}
    </Routes>
  );
}

export default App;
