import '../css_styles/Home.css';
import { useDispatch, useSelector } from "react-redux";
import { useRef } from 'react';
import { addItem } from '../redux/store';
import { useNavigate } from 'react-router-dom';

export default function Home() {

    const userState = useSelector((state) => state.user.isLoggedIn);
    const dispatch = useDispatch();
    const scrollRef = useRef(null);
    const navigate = useNavigate();

    const items = [
        {
            itemId: 1,
            isNewlyLaunched: false,
            itemImage: "⌚",
            itemQuantity: 1,
            itemCategory: "Accessories",
            itemName: "Classic Leather Watch",
            itemCurrentPrice: 129.00,
            itemOldPrice: 150.00
        },
        {
            itemId: 2,
            isNewlyLaunched: true,
            itemImage: "🎧",
            itemQuantity: 1,
            itemCategory: "Electronics",
            itemName: "Wireless Earbuds",
            itemCurrentPrice: 89.00,
            itemOldPrice: 100.00
        },
        {
            itemId: 3,
            isNewlyLaunched: false,
            itemImage: "💡",
            itemQuantity: 1,
            itemCategory: "Home",
            itemName: "Minimalist Lamp",
            itemCurrentPrice: 45.00,
            itemOldPrice: 0.00
        },
        {
            itemId: 4,
            isNewlyLaunched: false,
            itemImage: "📱",
            itemQuantity: 1,
            itemCategory: "Electronics",
            itemName: "iPhone 17 Pro",
            itemCurrentPrice: 1029.00,
            itemOldPrice: 1050.00
        },
        {
            itemId: 5,
            isNewlyLaunched: true,
            itemImage: "👟",
            itemQuantity: 1,
            itemCategory: "Fashion",
            itemName: "Sport Sneakers",
            itemCurrentPrice: 75.00,
            itemOldPrice: 90.00
        }
    ];

    const scroll = (direction) => {
        const { current } = scrollRef;
        if (current) {
            const scrollAmount = 330; // Card width + gap
            current.scrollBy({
                left: direction === 'left' ? -scrollAmount : scrollAmount,
                behavior: 'smooth'
            });
        }
    };

    const addToCart = (item) => {
        if(userState) dispatch(addItem(item));
        else navigate('/login');
    }

    return (
        <div id="shopease-home">
            {/* Hero Section */}
            <header className="hero-banner">
                <div className="hero-overlay">
                    <div className="hero-text">
                        <h1>Welcome to <span className="brand-accent">ShopEase</span></h1>
                        <p>Modern essentials delivered to your doorstep. Simple. Fast. Reliable.</p>
                        <button className="cta-button">Explore Collections</button>
                    </div>
                </div>
            </header>

            {/* Section: Trending items */}
            <section className="item-section">
                <div className="section-header">
                    <h2>Trending Now</h2>
                    <div className="underline"></div>
                </div>

                <div className="slider-container">
                    {/* Navigation Buttons */}
                    <button className="nav-btn prev" onClick={() => scroll('left')}>❮</button>
                    <button className="nav-btn next" onClick={() => scroll('right')}>❯</button>

                    {/* The Scrollable Grid */}
                    <div className="item-grid" ref={scrollRef}>
                        {items.map((item, index) => (
                            <div className="item-card" key={index}>
                                {item.isNewlyLaunched && <div className="badge">New</div>}
                                <div className="item-image-placeholder">
                                    <span>{item.itemImage}</span>
                                </div>
                                <div className="item-details">
                                    <span className="category-tag">{item.itemCategory}</span>
                                    <h3>{item.itemName}</h3>
                                    <div className="price-row">
                                        <span className="current-price">${item.itemCurrentPrice}</span>
                                        {item.itemOldPrice > 0 && (
                                            <span className="old-price">${item.itemOldPrice}</span>
                                        )}
                                    </div>
                                    <button className="add-btn" onClick={() => addToCart(item)}>Add to Cart</button>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </section>
        </div>
    );
}