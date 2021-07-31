## Indobata Rest API Documentation

## Example 1: 
**Show Product**
----
  Returns json data about a list of product.

* **URL**

  /api/product

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `    
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
    }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{
    "timestamp": "2021-07-31T16:37:02.728+00:00",
    "status": 404,
    "error": "Not Found",
    "path": "/api/products"
}`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```typescript:
      getAllProduct(): Observable<Product[]> {
        return this.http.get<any>(environment.baseEndpoint + 'api/product');
      }
  ```
