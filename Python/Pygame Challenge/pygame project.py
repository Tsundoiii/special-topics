from random import randrange

import pygame

GRID_VALUES = [[[] for _ in range(20)] for _ in range(20)]

with open("pygameinput.txt") as input_file:
    for line in input_file.readlines():
        color, row, col = line.split()
        GRID_VALUES[int(row)][int(col)] = {"r": 0, "y": 1, "b": 2, "g": 3}[color]

# Constants
WIDTH, HEIGHT = 500, 500  # Window size
GRID_SIZE = 20  # Size of the grid
CELL_SIZE = WIDTH // GRID_SIZE  # Size of each cell

# Colors for different integer values (0-9)
COLORS = [
    (255, 0, 0),  # Red
    (255, 255, 0),  # Yellow
    (0, 0, 255),  # Blue
    (0, 255, 0),  # Green
]

BLACK = (0, 0, 0)


def draw_grid(surface, grid):
    for row in range(GRID_SIZE):
        if all(i == grid[row][0] for i in grid[row]):
            for j in range(row - 1, -1, -1):
                grid[j + 1] = grid[j]

            grid[0] = [randrange(4) for _ in grid[0]]
        else:
            for col in range(GRID_SIZE):
                value = grid[row][col]
                color = COLORS[value]

                x = col * CELL_SIZE
                y = row * CELL_SIZE

                pygame.draw.rect(surface, color, (x, y, CELL_SIZE, CELL_SIZE))


def update_square(grid, row, col):
    grid[row][col] = (grid[row][col] + 1) % 4


def handle_events(grid):
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            return False
        elif event.type == pygame.MOUSEBUTTONDOWN and event.button == 1:  # Left click
            x, y = pygame.mouse.get_pos()
            row = y // CELL_SIZE
            col = x // CELL_SIZE
            update_square(grid, row, col)
    return True


def main():
    # Initialize Pygame
    pygame.init()
    screen = pygame.display.set_mode((WIDTH, HEIGHT))
    pygame.display.set_caption("Pygame Project")
    clock = pygame.time.Clock()
    running = True

    # Main loop
    while running:
        running = handle_events(GRID_VALUES)
        screen.fill((255, 255, 255))  # Background color
        draw_grid(screen, GRID_VALUES)
        pygame.display.flip()
        clock.tick(30)

    pygame.quit()


if __name__ == "__main__":
    main()
