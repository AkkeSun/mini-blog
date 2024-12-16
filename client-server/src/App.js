import './App.css';
import MainPage from "./component/page/MainPage";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import PostWritePage from "./component/page/PostWritePage";
import PostDetailPage from "./component/page/PostViewPage";

const queryClient = new QueryClient();

function App() {
  return (
      <BrowserRouter>
        <QueryClientProvider client={queryClient}>
          <Routes>
            <Route path="/" element={<MainPage/>}/>
            <Route path="/post-write" element={<PostWritePage/>}/>
            <Route path="/posts/:id" element={<PostDetailPage/>}/>
          </Routes>
        </QueryClientProvider>
      </BrowserRouter>
  );
}

export default App;
