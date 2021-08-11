# Indobata API Documentation

## Development

1. Clone this repository
    ```
    git clone https://github.com/MulyonoPutra/Indobata.git
    ```

2. Change current directory to this repository folder
    ```
    cd Indobata
    ```
    
3. Install dependencies
    ```
    ./mvnw or mvnw
    ```
    
4. Run the server
    
    - Development mode
        ```
        mvn spring-boot:run
        ```
    

## Usage

Base URL : `http://localhost:8080/`

### Endpoint

| Endpoint      | Description                                 | Parameter | Method |
| ---------     | ------------------------------------------- | --------- | ------ |
| `/product`    | Only the start page shows basic information | No        | GET    |
| `/product`    | Only the start page shows basic information | No        | POST   |
| `/product`    | Only the start page shows basic information | No        | DELETE |
| `/product`    | Only the start page shows basic information | No        | PUT    |

### Example

#### Request

```curl
curl -XGET 'http://localhost:8080/product'
```

#### Response

```json
    {
        "id": 4,
        "productName": "Kanstin White",
        "description": "Kanstin White",
        "sku": "PR000001",
        "size": "12cm",
        "price": "15000",
        "images":"",
        "imagesContentType": "image/png",
        "category_product": {
            "id": 1,
            "name": "Genteng Beton"
        }
    }
```

#### Error Response
```json
    {
        "timestamp": "2021-07-31T16:37:02.728+00:00",
        "status": 404,
        "error": "Not Found",
        "path": "/api/products"
    }
```
OR

* **Code:** 401 UNAUTHORIZED <br />
**Content:** `{ error : "You are unauthorized to make this request." }`


* **Sample Call:**

  ```typescript:
      getAllProduct(): Observable<Product[]> {
        return this.http.get<any>(environment.baseEndpoint + 'api/product');
      }
  ```

## Additional Information
-
