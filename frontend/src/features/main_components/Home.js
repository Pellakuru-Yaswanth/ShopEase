import { useRef } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from 'react-router-dom';
import ItemCard from '../catalog/ItemCard';
import '../../styles/main/Home.css';

export default function Home() {
    
    const navigate = useNavigate();
    const scrollRef = useRef(null);

    // Catalog Data (This usually comes from your ProductController)
    const items = [
        {
            productId: 1,
            isNewlyLaunched: false,
            itemImage: "⌚",
            category: "Accessories" ,
            name: "Classic Leather Watch",
            price: 129.00,
            oldPrice: 150.00
        },
        {
            productId: 2,
            isNewlyLaunched: true,
            itemImage: "🎧",
            category: "Electronics",
            name: "Wireless Earbuds",
            price: 89.00,
            oldPrice: 100.00
        },
        {
            productId: 3,
            isNewlyLaunched: false,
            itemImage: "💡",
            category: "Home",
            name: "Minimalist Lamp",
            price: 45.00,
            oldPrice: 0.00
        },
        {
            productId: 4,
            isNewlyLaunched: false,
            itemImage: "📱",
            category: "Electronics",
            name: "iPhone 17 Pro",
            price: 1029.00,
            oldPrice: 1050.00
        },
        {
            productId: 5,
            isNewlyLaunched: true,
            itemImage: "👟",
            category: "Fashion",
            name: "Sport Sneakers",
            price: 75.00,
            oldPrice: 90.00
        }
    ];

    // Slider Logic
    const scroll = (direction) => {
        const { current } = scrollRef;
        if (current) {
            const scrollAmount = 330; // Matches card width + gap
            current.scrollBy({
                left: direction === 'left' ? -scrollAmount : scrollAmount,
                behavior: 'smooth'
            });
        }
    };

    return (
        <div id="shopease-home">
            {/* Hero Section */}
            <header className="hero-banner">
                <div className="hero-overlay">
                    <div className="hero-text">
                        <h1>Welcome to <span className="brand-accent">ShopEase</span></h1>
                        <p>Modern essentials delivered to your doorstep. Simple. Fast. Reliable.</p>
                        <button className="cta-button" onClick={() => navigate('/shop')}>Explore Collections</button>
                    </div>
                </div>
            </header>

            {/* Trending Items Section */}
            <section className="item-section">
                <div className="section-header">
                    <h2>Trending Now</h2>
                    <div className="underline"></div>
                </div>

                <div className="slider-container">
                    {/* Horizontal Navigation */}
                    <button className="nav-btn prev" onClick={() => scroll('left')}>❮</button>
                    <button className="nav-btn next" onClick={() => scroll('right')}>❯</button>

                    {/* Product Grid (Flexbox Scroll) */}
                    <div className="item-grid" ref={scrollRef}>
                        {items.map((item) => <ItemCard item={item}/>)}
                    </div>
                </div>
            </section>
        </div>
    );
}