package leetcode;

import java.util.PriorityQueue;

/**
 * @description: Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
 * @author: LISHUAI
 * @createDate: 2022/9/18 22:41
 * @version: 1.0
 */

public class LeetCode_407 {


    int[] dx = {0, -1, 0, 1};
    int[] dy = {-1, 0, 1, 0};

    int newX = 0, newY = 0;

    private void processBoundary(int[][] heightMap, boolean[][] visited, PriorityQueue<HeapEntry> minHeap) {
        int n = heightMap.length;
        int m = heightMap[0].length;

        //first and last column
        for (int i = 0; i < n; i++) {
            //first column
            visited[i][0] = true;
            minHeap.add(new HeapEntry(i, 0, heightMap[i][0]));

            //last column
            visited[i][m - 1] = true;
            minHeap.add(new HeapEntry(i, m - 1, heightMap[i][m - 1]));
        }

        //first and last row
        for (int j = 0; j < m; j++) {
            //first row
            visited[0][j] = true;
            minHeap.add(new HeapEntry(0, j, heightMap[0][j]));

            //last row
            visited[n - 1][j] = true;
            minHeap.add(new HeapEntry(n - 1, j, heightMap[n - 1][j]));
        }
    }

    private boolean isSafe(int[][] heightMap, boolean[][] visited, int x, int y) {
        int n = heightMap.length;
        int m = heightMap[0].length;

        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }

        return visited[x][y] == false;
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) {
            return 0;
        }

        int n = heightMap.length; //rows
        int m = heightMap[0].length; //columns

        boolean[][] visited = new boolean[n][m];

        //min heap, based on height
        PriorityQueue<HeapEntry> minHeap = new PriorityQueue<>((i1, i2) -> (i1.height - i2.height));

        int totalTrappedWater = 0;

        //process boundary
        processBoundary(heightMap, visited, minHeap);

        HeapEntry heapEntry = null;
        int x = 0, y = 0, height = 0;
        while (!minHeap.isEmpty()) {
            heapEntry = minHeap.poll();

            x = heapEntry.x;
            y = heapEntry.y;
            height = heapEntry.height;

            //explore 4 neighbors
            for (int k = 0; k < 4; k++) {
                newX = x + dx[k];
                newY = y + dy[k];

                if (isSafe(heightMap, visited, newX, newY)) {
                    //mark new position as visited
                    visited[newX][newY] = true;

                    //3 cases
                    if (heightMap[newX][newY] < height) {
                        //comparing with height, not heightMap[x][y] because it gets replaced when we add to heaps in following 3 cases

                        totalTrappedWater += height - heightMap[newX][newY];
                        //put the entry to heap
                        minHeap.add(new HeapEntry(newX, newY, height));
                    } else if (heightMap[newX][newY] == height) {
                        //no change to trapped water
                        //put the entry to heap
                        minHeap.add(new HeapEntry(newX, newY, height));
                    } else {
                        //heightMap[newX][newY] > height
                        //no change to trapped water
                        //put entry to heap
                        minHeap.add(new HeapEntry(newX, newY, heightMap[newX][newY]));
                    }
                }
            }
        }

        return totalTrappedWater;
    }

    class HeapEntry {
        int x;
        int y;
        int height;

        public HeapEntry(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

}
