# Installation
1st step to create Secrets with name 'ps-bff-secrets' which contains  user credentials for 
PostgreSQL database (url, used name and password).


kubectl create secret generic ps-bff-secrets \
    --from-literal='AZURE_TENANT_ID=...'  \
    --from-literal='AZURE_CLIENT_ID=...' \
    --from-literal='AZURE_CLIENT_SECRET=...'  \
    --from-literal='OAUTH_CLIENT_ID=...'  \
    --from-literal='OAUTH_CLIENT_SECRET=...'

