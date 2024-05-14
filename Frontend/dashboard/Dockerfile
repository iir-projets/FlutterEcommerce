# Step 1: Build the React application
FROM node:14-alpine as build

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json (or yarn.lock)
COPY package*.json ./
COPY yarn.lock ./

# Install dependencies
RUN yarn install

# Copy the rest of your app's source code
COPY . ./

# Build the application
RUN yarn build

# Step 2: Serve the app using Nginx
FROM nginx:stable-alpine

# Copy built assets from builder stage
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx and keep it running
CMD ["nginx", "-g", "daemon off;"]
