import pygame

# Constants
WIDTH, HEIGHT = 500, 500  # Window size
GRID_SIZE = 10  # Size of the grid
CELL_SIZE = WIDTH // GRID_SIZE  # Size of each cell

# Colors for different integer values (0-9)
COLORS = [
    (255, 255, 255),  # 0 - White
    (255, 0, 0),  # 1 - Red
    (0, 255, 0),  # 2 - Green
    (0, 0, 255),  # 3 - Blue
    (255, 255, 0),  # 4 - Yellow
    (255, 165, 0),  # 5 - Orange
    (255, 0, 255),  # 6 - Magenta
    (0, 255, 255),  # 7 - Cyan
    (128, 0, 128),  # 8 - Purple
    (0, 0, 0),  # 9 - Black
]

# Sample 10x10 2D list with integer values
GRID_VALUES = [
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 0],
    [0, 9, 8, 7, 6, 5, 4, 3, 2, 1],
    [1, 0, 1, 0, 1, 0, 1, 0, 1, 0],
    [2, 3, 4, 5, 6, 7, 8, 9, 0, 1],
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 0],
    [0, 9, 8, 7, 6, 5, 4, 3, 2, 1],
    [1, 0, 1, 0, 1, 0, 1, 0, 1, 0],
    [2, 3, 4, 5, 6, 7, 8, 9, 0, 1],
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 0],
    [0, 9, 8, 7, 6, 5, 4, 3, 2, 1],
]


def draw_grid(surface, grid):
    for row in range(GRID_SIZE):
        for col in range(GRID_SIZE):
            value = grid[row][col]
            color = COLORS[value]

            x = col * CELL_SIZE
            y = row * CELL_SIZE

            pygame.draw.rect(surface, color, (x, y, CELL_SIZE, CELL_SIZE))


def update_square(grid, row, col):
    grid[row][col] = 9


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
    pygame.display.set_caption("Grid Visualizer")
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
