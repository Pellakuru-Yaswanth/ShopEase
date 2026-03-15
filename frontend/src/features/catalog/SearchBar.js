import '../../styles/catalog/SearchBar.css'
export default function SearchBar () {

    return (
        <div className="search-container">
            <input type="text" placeholder="Search for products..." />
            <button className="search-icon-btn">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5">
                    <circle cx="11" cy="11" r="8"></circle>
                    <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
                </svg>
            </button>
        </div>
    );
}