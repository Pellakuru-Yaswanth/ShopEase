import '../css_styles/ShowItems.css';

import { useState } from 'react';

export default function ShowItems() {

    const [filters, setFilters] = useState({
        categories: [],
        priceRange: 0
    })

    const [items, setItems] = useState([
        { id: 1, name: "Premium Wireless Headphones", price: 199.00, category: "Electronics", image: "🎧" },
        { id: 2, name: "Minimalist Leather Wallet", price: 45.00, category: "Accessories", image: "👛" },
        { id: 3, name: "Smart Fitness Watch", price: 129.00, category: "Electronics", image: "⌚" },
        { id: 4, name: "Cotton Casual T-Shirt", price: 25.00, category: "Fashion", image: "👕" },
        { id: 5, name: "Mechanical Gaming Keyboard", price: 89.00, category: "Electronics", image: "⌨️" },
        { id: 6, name: "Designer Sunglasses", price: 150.00, category: "Fashion", image: "🕶️" },
    ]);

    const [updatedItems, setUpdatedItems] = useState(items);

    const sortByPrice = (type) => {
        if(type=='asc'){
            setUpdatedItems(prevItems => [...prevItems].sort((a, b) => a.price-b.price));
        } else {
            setUpdatedItems(prevItems => [...prevItems].sort((a, b) => b.price-a.price));
        }
    }

    const sortTypeHandler = (e) => {
        let type = e.target.value;
        sortByPrice(type);
    }

    const handlePriceRange = (e) => {
        console.log(e.target.value);
        setFilters(prevFilters => ({
            ...prevFilters,
            priceRange: e.target.value
        }));
    }

    const applyFilters = () => {
        //Filtering using PriceRange only
        setUpdatedItems(prevItems => items.filter(item => item.price<=filters.priceRange));
    }

    return (
        <div id="items-container">
            {/* Sidebar for Filters */}
            <aside className="filter-sidebar">
                <h3>Filters</h3>
                <div className="filter-group">
                    <h4>Category</h4>
                    <label><input type="checkbox" /> Electronics</label>
                    <label><input type="checkbox" /> Fashion</label>
                    <label><input type="checkbox" /> Accessories</label>
                </div>
                <div className="filter-group">
                    <h4>Price Range</h4>
                    <input type="range" min="0" max="500" className="price-slider" onChange={handlePriceRange}/>
                    <div className="price-labels">
                        <span>$0</span>
                        <span>$500</span>
                    </div>
                </div>
                <button className="apply-filter-btn" onClick={applyFilters}>
                    Apply
                </button>
            </aside>

            {/* Main Content: Product Grid */}
            <main className="items-main">
                <div className="items-header">
                    <h2>All Items <span className="item-count">({updatedItems.length})</span></h2>
                    <select className="sort-dropdown" onChange={sortTypeHandler}>
                        <option value='asc'>Price: Low to High</option>
                        <option value='desc'>Price: High to Low</option>
                    </select>
                </div>

                <div className="product-grid">
                    {updatedItems.map(product => (
                        <div key={product.id} className="item-card">
                            <div className="item-image">
                                <span>{product.image}</span>
                            </div>
                            <div className="item-info">
                                <span className="item-category">{product.category}</span>
                                <h4>{product.name}</h4>
                                <p className="item-price">{product.price}</p>
                                <button className="add-to-cart-btn">Add to Cart</button>
                            </div>
                        </div>
                    ))}
                </div>
            </main>
        </div>
    );
}