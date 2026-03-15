import '../../styles/catalog/Shop.css';
import { useState } from 'react';
import ItemCard from './ItemCard';

export default function Shop() {
    
    const [filters, setFilters] = useState({
        categories: [],
        priceRange: 5000.00
    })

    const [items, setItems] = useState([
        {
            productId: 1,
            isNewlyLaunched: false,
            itemImage: "⌚",
            category: { name: "Accessories" },
            name: "Classic Leather Watch",
            price: 129.00,
            oldPrice: 150.00
        },
        {
            productId: 2,
            isNewlyLaunched: true,
            itemImage: "🎧",
            category: { name: "Electronics" },
            name: "Wireless Earbuds",
            price: 89.00,
            oldPrice: 100.00
        },
        {
            productId: 3,
            isNewlyLaunched: false,
            itemImage: "💡",
            category: { name: "Home" },
            name: "Minimalist Lamp",
            price: 45.00,
            oldPrice: 0.00
        },
        {
            productId: 4,
            isNewlyLaunched: false,
            itemImage: "📱",
            category: { name: "Electronics" },
            name: "iPhone 17 Pro",
            price: 1029.00,
            oldPrice: 1050.00
        },
        {
            productId: 5,
            isNewlyLaunched: true,
            itemImage: "👟",
            category: { name: "Fashion" },
            name: "Sport Sneakers",
            price: 75.00,
            oldPrice: 90.00
        }
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
                    <input type="range" min="0" max="5000" className="price-slider" onChange={handlePriceRange} value={filters.priceRange}/>
                    <div className="price-labels">
                        <span>$0</span>
                        <span>$5000</span>
                    </div>
                </div>
                <button className="apply-filter-btn" onClick={applyFilters}>
                    Apply
                </button>
            </aside>

            {/* Main Content: Product Grid */}
            <main className="items-main">
                <div className="items-header">
                    <select className="sort-dropdown" onChange={sortTypeHandler}>
                        <option value='asc'>Price: Low to High</option>
                        <option value='desc'>Price: High to Low</option>
                    </select>
                </div>

                <div className="product-grid">
                    {updatedItems.map((item) => <ItemCard item={item}/>)}
                </div>
            </main>
        </div>
    );
}