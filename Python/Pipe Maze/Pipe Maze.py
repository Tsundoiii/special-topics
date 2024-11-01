import pygame
import pygame.freetype

TEXT_SIZE = 16
HORIZONTAL_MULTIPLIER = 0.625
FPS = 30


def sinks(path: str, screen: pygame.Surface) -> str:
    font = pygame.freetype.SysFont("Courier", TEXT_SIZE)

    pipe_map: list[list[str]] = []

    with open(path, "r", encoding="utf-8") as input_file:
        for cell in input_file.readlines():
            if cell != "\n":
                split_cell = cell.split()
                state = split_cell[0]
                column = int(split_cell[1])
                row = int(split_cell[2])

                if state == "*":
                    source: tuple = (30 - 1 - row, column)
                    print(source)

                while len(pipe_map) <= row:
                    pipe_map.append([])

                while len(pipe_map[row]) <= column:
                    pipe_map[row].append(" ")

                pipe_map[row][column] = split_cell[0]

        pipe_map.reverse()

    flows: dict[str, list[str]] = {
        "═": ["e", "w"],
        "║": ["n", "s"],
        "╔": ["e", "s"],
        "╗": ["w", "s"],
        "╚": ["n", "e"],
        "╝": ["n", "w"],
        "╠": ["n", "s", "e"],
        "╣": ["n", "s", "w"],
        "╦": ["e", "w", "s"],
        "╩": ["n", "e", "w"],
        " ": [],
        "*": ["n", "e", "s", "w"],
    }
    accepts: dict[str, list[str]] = {
        "═": ["e", "w"],
        "║": ["n", "s"],
        "╔": ["n", "w"],
        "╗": ["n", "e"],
        "╚": ["s", "w"],
        "╝": ["s", "e"],
        "╠": ["n", "w", "s"],
        "╣": ["n", "s", "e"],
        "╦": ["w", "n", "e"],
        "╩": ["s", "w", "e"],
        " ": ["n", "e", "s", "w"],
        "*": ["n", "e", "s", "w"],
    }

    def neighboring_cells(
        current_cell: tuple[int, int]
    ) -> list[tuple[int, int] | None]:
        return [
            (
                (current_cell[0] - 1, current_cell[1])
                if current_cell[0] - 1 >= 0
                else None
            ),
            (
                (current_cell[0], current_cell[1] + 1)
                if current_cell[1] + 1 < len(pipe_map[0])
                else None
            ),
            (
                (current_cell[0] + 1, current_cell[1])
                if current_cell[0] + 1 < len(pipe_map)
                else None
            ),
            (
                (current_cell[0], current_cell[1] - 1)
                if current_cell[1] - 1 >= 0
                else None
            ),
        ]

    path: list[tuple[int, int]] = [source]
    visited: set[tuple[int, int]] = {source}
    sinks: set[str] = set()

    while True:
        print(f"Path: {path}")

        if not path:
            return "".join(sorted(sinks))

        screen.fill((255, 255, 255))
        for i, line in enumerate(pipe_map):
            font.render_to(screen, (0, i * font.size), "".join(line))

            for j, cell in enumerate(line):
                if (i, j) in visited:
                    overlay = pygame.Surface(
                        (
                            font.size * HORIZONTAL_MULTIPLIER,
                            font.size,
                        )
                    )
                    overlay.set_alpha(64)
                    overlay.fill((0, 0, 255))
                    screen.blit(
                        overlay,
                        (j * font.size * HORIZONTAL_MULTIPLIER, i * font.size),
                    )

                if (i, j) in path:
                    overlay = pygame.Surface(
                        (
                            font.size * HORIZONTAL_MULTIPLIER,
                            font.size,
                        )
                    )
                    overlay.set_alpha(64)
                    overlay.fill((0, 255, 0))
                    screen.blit(
                        overlay,
                        (j * font.size * HORIZONTAL_MULTIPLIER, i * font.size),
                    )

                if cell.isalpha() and cell in sinks:
                    overlay = pygame.Surface(
                        (
                            font.size * HORIZONTAL_MULTIPLIER,
                            font.size,
                        )
                    )
                    overlay.set_alpha(64)
                    overlay.fill((0, 255, 255))
                    screen.blit(
                        overlay,
                        (j * font.size * HORIZONTAL_MULTIPLIER, i * font.size),
                    )

        overlay.set_alpha(64)
        overlay.fill((255, 0, 0))
        screen.blit(
            overlay,
            (path[-1][1] * font.size * HORIZONTAL_MULTIPLIER, path[-1][0] * font.size),
        )

        overlay = pygame.Surface(
            (
                font.size * HORIZONTAL_MULTIPLIER,
                font.size,
            )
        )
        overlay.set_alpha(64)
        overlay.fill((255, 0, 0))
        screen.blit(
            overlay,
            (path[-1][1] * font.size * HORIZONTAL_MULTIPLIER, path[-1][0] * font.size),
        )

        for cell, direction in zip(
            neighboring_cells(path[-1]),
            ("n", "e", "s", "w"),
        ):
            if cell in visited or cell is None:
                continue

            if pipe_map[cell[0]][cell[1]].isalpha() and (
                (
                    pipe_map[path[-1][0]][path[-1][1]].isalpha()
                    or direction in flows[pipe_map[path[-1][0]][path[-1][1]]]
                )
            ):
                sinks.add(pipe_map[cell[0]][cell[1]])

            if (
                pipe_map[path[-1][0]][path[-1][1]].isalpha()
                or direction in flows[pipe_map[path[-1][0]][path[-1][1]]]
            ) and (
                pipe_map[cell[0]][cell[1]].isalpha()
                or direction in accepts[pipe_map[cell[0]][cell[1]]]
            ):
                path.append(cell)
                visited.add(cell)
                break
        else:
            path.pop()

        pygame.display.flip()
        clock.tick(FPS)


if __name__ == "__main__":
    pygame.init()
    screen = pygame.display.set_mode(
        (50 * TEXT_SIZE * HORIZONTAL_MULTIPLIER, 30 * TEXT_SIZE)
    )
    screen.fill((255, 255, 255))
    pygame.display.set_caption("Pipe Maze")
    clock = pygame.time.Clock()

    print(f"Sinks: {sinks('input.txt', screen)}")

    pygame.quit()
