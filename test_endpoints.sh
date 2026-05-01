#!/bin/bash

BASE_URL="http://localhost:8081"

echo "--- Testing Produit Endpoints ---"
# Create a product
echo "1. Creating a product..."
PRODUIT_JSON='{"nom": "Clavier RGB", "prix": 59.99, "quantite": 100}'
PRODUIT_RESPONSE=$(curl -s -X POST "$BASE_URL/produits" -H "Content-Type: application/json" -d "$PRODUIT_JSON")
echo "Response: $PRODUIT_RESPONSE"
# Extract the first id (Product ID)
PRODUIT_ID=$(echo $PRODUIT_RESPONSE | grep -o '"id":[0-9]*' | head -1 | cut -d: -f2)
echo "Created Product ID: $PRODUIT_ID"

# Get all products
echo -e "\n2. Listing all products..."
curl -s -X GET "$BASE_URL/produits"
echo -e "\n"

echo -e "\n--- Testing Facture Endpoints ---"
# Create a facture with a line item
echo "3. Creating a facture with a line item..."
FACTURE_JSON='{
  "numFacture": "FACT-2026-001",
  "dateFacture": "2026-05-01",
  "numClient": "CUST-99",
  "ligneFactures": [
    {
      "quantite": 2,
      "produit": {"id": '"$PRODUIT_ID"'}
    }
  ]
}'
FACTURE_RESPONSE=$(curl -s -X POST "$BASE_URL/factures" -H "Content-Type: application/json" -d "$FACTURE_JSON")
echo "Response: $FACTURE_RESPONSE"
# Extract the first id (Facture ID)
FACTURE_ID=$(echo $FACTURE_RESPONSE | grep -o '"id":[0-9]*' | head -1 | cut -d: -f2)
echo "Created Facture ID: $FACTURE_ID"

# Get all factures
echo -e "\n4. Listing all factures..."
curl -s -X GET "$BASE_URL/factures"
echo -e "\n"

echo -e "\n--- Testing LigneFacture Endpoints ---"
# Get all ligne_factures
echo "5. Listing all invoice lines..."
curl -s -X GET "$BASE_URL/lignes-facture"
echo -e "\n"

echo -e "\n--- Testing Valeur Stock Endpoint ---"
echo "6. Checking total stock value..."
curl -s -X GET "$BASE_URL/valeur"
echo -e "\n"

echo "--- Cleanup ---"
if [ ! -z "$FACTURE_ID" ]; then
  echo "Deleting Facture $FACTURE_ID..."
  curl -s -X DELETE "$BASE_URL/factures/$FACTURE_ID"
fi

if [ ! -z "$PRODUIT_ID" ]; then
  echo "Deleting Product $PRODUIT_ID..."
  curl -s -X DELETE "$BASE_URL/produits/$PRODUIT_ID"
fi

echo -e "\nTests completed!"
