const fetchUsers = () => {
    fetch("http://localhost:8081/user/getAllUsers").then(res => res.json()).then(data => {
      let tempUsers = [];
      console.log(data);
        // for(let user of data){
        //   tempUsers.push(user);
        // }
      console.log(tempUsers);
    }).catch((e) => console.log(e));
  }

const insertUsers = () => {
    console.log("InsertUsers started");
    for(let i=0; i<100; i++){
      fetch("http://localhost:8081/user/createUser", {
        method: 'POST',
        headers: {
          'content-type': 'application/json'
        },
        body: JSON.stringify({
          'userId': 1,
          'password': 'Yash@Hi'+(i+1), 
          'fullName': 'Yaswanth Pellakuru'+(i+1), 
          'countryCode': '+91',
            'phoneNumber': 9014681469,
            'email': 'yaswanth.pellakuru'+i+'@gmail.com'
        })
      }).then(res => res.json()).then(data => console.log(data));
    }
    console.log("InsertUsers completed");
  }

const insertItems = () => {
    console.log("InsertItems started");
    for(let i=0; i<100; i++){
      fetch("http://localhost:8081/item/createItem", {
        method: 'POST',
        headers: {
          'content-type': 'application/json'
        },
        body: JSON.stringify({
          'itemId': 1,
          'itemName': 'Apple'+(i+1), 
          'itemDescription': 'Apple Fruite'+(i+1), 
          'quantity': i*5,
          'price': 45.0,
          'picture': 'profile picture for items'
        })
      }).then(res => res.json()).then(data => console.log(data));
    }
    console.log("InsertItems completed");
  }
insertUsers();
//insertItems();
//fetchUsers();